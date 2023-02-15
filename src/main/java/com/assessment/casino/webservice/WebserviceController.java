package com.assessment.casino.webservice;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.assessment.casino.business.ReservationService;
import com.assessment.casino.business.RoomReservation;
import com.assessment.casino.data.Guest;
import com.assessment.casino.data.Room;
import com.assessment.casino.util.DateUtils;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebserviceController {

  private final DateUtils dateUtils;
  private final ReservationService reservationService;

  public WebserviceController(
    DateUtils dateUtils,
    ReservationService reservationService
  ) {
    this.dateUtils = dateUtils;
    this.reservationService = reservationService;
  }

  @RequestMapping(path = "/reservations", method = GET)
  public List<RoomReservation> getReservations(
    @RequestParam(value = "date", required = false) String dateString
  ) {
    Date date = this.dateUtils.createDateFromDateString(dateString);
    return this.reservationService.getRoomReservationsForDate(date);
  }

  @GetMapping("/guests")
  public List<Guest> getGuests() {
    return this.reservationService.getHotelGuests();
  }

  @PostMapping("/guests")
  @ResponseStatus(HttpStatus.CREATED)
  public void addGuest(@RequestBody Guest guest) {
    this.reservationService.addGuest(guest);
  }

  @GetMapping("/rooms")
  public List<Room> getRooms() {
    return this.reservationService.getRooms();
  }
}
