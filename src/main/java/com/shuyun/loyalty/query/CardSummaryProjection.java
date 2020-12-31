package com.shuyun.loyalty.query;

import com.shuyun.loyalty.event.IssuedEvt;
import com.shuyun.loyalty.event.RedeemedEvt;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class CardSummaryProjection {

    private final List<CardSummary> cardSummaries = new CopyOnWriteArrayList<>();

    private final CardSummaryRepository cardSummaryRepository;

    @Autowired
    public CardSummaryProjection(CardSummaryRepository cardSummaryRepository) {
        this.cardSummaryRepository = cardSummaryRepository;
    }

    @EventHandler // (1)
    public void on(IssuedEvt evt) {
        System.out.println("EventHandler on(IssuedEvt evt) ");
        CardSummary cardSummary = new CardSummary(evt.getId(), evt.getAmount(), evt.getAmount());
        cardSummaries.add(cardSummary);
        cardSummaryRepository.saveAndFlush(cardSummary);
    }

    @EventHandler
    public void on(RedeemedEvt evt) {
        System.out.println("EventHandler on(RedeemedEvt evt) ");
        Optional<CardSummary> cardSummary = cardSummaryRepository.findById(evt.getId());
        cardSummary.ifPresent(s -> {
            s.deductAmount(evt.getAmount());
            cardSummaryRepository.saveAndFlush(s);
        });
        // cardSummaries.stream().filter(cs -> evt.getId().equals(cs.getId())).findFirst()
        // .ifPresent(cardSummary -> {
        // CardSummary updatedCardSummary = cardSummary.deductAmount(evt.getAmount());
        // // cardSummaries.remove(cardSummary);
        // // cardSummaries.add(updatedCardSummary);
        // cardSummaryRepository.saveAndFlush(cardSummary);
        // });
    }

    @QueryHandler // (2)
    public List<CardSummary> fetch(FetchCardSummariesQuery query) {
        return cardSummaryRepository.findAll().stream().skip(query.getOffset())
                .limit(query.getSize()).collect(Collectors.toList());
    }
}
