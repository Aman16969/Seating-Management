package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.Location;
import com.example.SeatingManagement.Entity.RequestSeat;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.RequestSeatRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.EmailService;
import com.example.SeatingManagement.Services.RequestSeatService;
import com.example.SeatingManagement.utils.EmailBody;
import com.example.SeatingManagement.utils.SeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestSeatImple implements RequestSeatService {
    @Autowired
    private RequestSeatRepository requestSeatRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public RequestSeat createNewSeatRequest(SeatRequest seatRequest) {
        Location location = this.locationRepository.findById(seatRequest.getLocationId()).orElseThrow(()->new ResourceNotFound("Location", "id", seatRequest.getLocationId().toString()));
        User user = this.userRepository.findByEmail(seatRequest.getEmail()).orElseThrow(()->new ResourceNotFound("User", "user email", seatRequest.getEmail()));
        RequestSeat newReq=new RequestSeat();
        newReq.setDate(seatRequest.getDate());
        newReq.setFromTime(seatRequest.getFromTime());
        newReq.setToTime(seatRequest.getToTime());
        newReq.setUser(user);
        newReq.setLocation(location);
        newReq.setDescription(seatRequest.getDescription());
        newReq.setAccepted(false);
        newReq.setActive(true);
        RequestSeat newRequestSeat = this.requestSeatRepository.save(newReq);
        EmailBody emailBody = new EmailBody();
        emailBody.setToEmail(user.getEmail());
        emailBody.setSubject("Seat Request Confirmation for "+location.getName()+" location.");
        emailBody.setMessage("Dear "+user.getFirstName()+",\n" +
                "\n" +
                "This is to confirm that we have received your request for a seat at our "+location.getName()+" location on "+newReq.getDate()+" during the "+newReq.getFromTime()+" to "+newReq.getToTime()+" time slot.\n" +
                "\n" +
                "We will review your request and make every effort to accommodate your seating requirements. We will notify you as soon as possible once your booking has been confirmed.\n" +
                "\n" +
                "Thank you for using our seat booking system. If you have any questions or concerns, please do not hesitate to contact us.\n" +
                "\n" +
                "Best regards,\n" +
                "Accolite Digital");
        this.emailService.sendMail(emailBody);
        return newRequestSeat;
    }

    @Override
    public RequestSeat updateSeatRequest(RequestSeat requestSeat) {
        RequestSeat updatedRequestSeat = this.requestSeatRepository.save(requestSeat);
        return updatedRequestSeat;
    }

    @Override
    public RequestSeat cancelSeatRequest(Integer id) {
        RequestSeat requestSeat = this.requestSeatRepository.findById(id).orElseThrow(()->new ResourceNotFound("RequestSeat", "id", id.toString()));
        requestSeat.setActive(false);
        RequestSeat cancelledRequestSeat = this.requestSeatRepository.save(requestSeat);
        return cancelledRequestSeat;
    }

    @Override
    public RequestSeat acceptSeatRequest(Integer id) {
        RequestSeat requestSeat = this.requestSeatRepository.findById(id).orElseThrow(()->new ResourceNotFound("RequestSeat", "id", id.toString()));
        requestSeat.setAccepted(true);
        RequestSeat acceptedRequestSeat = this.requestSeatRepository.save(requestSeat);
        return acceptedRequestSeat;
    }
    @Override
    public List<RequestSeat> getAllLocationRequests(Integer locationId) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(()->new ResourceNotFound("Location", "id", locationId.toString()));
        List<RequestSeat> requestSeats = this.requestSeatRepository.findByLocationAndIsActive(location, true);
        return requestSeats;
    }

    @Override
    public List<RequestSeat> getAllUserRequests(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User", "user_id", userId.toString()));
        List<RequestSeat> requestSeats = this.requestSeatRepository.findByUserAndIsActive(user, true);
        return requestSeats;
    }


    @Override
    public RequestSeat getRequestById(Integer id) {
        RequestSeat requestSeat = this.requestSeatRepository.findById(id).orElseThrow(()->new ResourceNotFound("RequestSeat", "id", id.toString()));
        return requestSeat;
    }
}
