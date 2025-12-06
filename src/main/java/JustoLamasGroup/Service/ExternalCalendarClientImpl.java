package JustoLamasGroup.Service;

import JustoLamasGroup.DTO.ReservationExternalRequest;
import JustoLamasGroup.DTO.ShowDateExternalRequest;
import JustoLamasGroup.Entity.ShowDate;
import JustoLamasGroup.Entity.TicketReservation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalCalendarClientImpl implements ExternalCalendarClient {

    private final WebClient webClient;

    public ExternalCalendarClientImpl(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.calendario-de-tu-cliente.com") // ajustar
                .build();
    }

    @Override
    public void createShowDate(ShowDate showDate) {
        ShowDateExternalRequest dto = ShowDateExternalRequest.fromEntity(showDate);

        webClient.post()
                .uri("/events")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public void createReservation(TicketReservation reservation) {
        ReservationExternalRequest dto = ReservationExternalRequest.fromEntity(reservation);

        webClient.post()
                .uri("/reservations")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
