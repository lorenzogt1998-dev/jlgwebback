// src/main/java/JustoLamasGroup/Controller/TourController.java
package JustoLamasGroup.Controller;

import JustoLamasGroup.Entity.Tour;
import JustoLamasGroup.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin(origins = "http://localhost:5173")
public class TourController {

    private final BookingService bookingService;

    public TourController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Tour create(@RequestBody Tour tour) {
        return bookingService.createTour(tour);
    }

    @GetMapping
    public List<Tour> list() {
        return bookingService.getAllTours();
    }

    // eliminar un tour por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }
}
