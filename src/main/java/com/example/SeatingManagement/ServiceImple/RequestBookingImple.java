package com.example.SeatingManagement.ServiceImple;

import com.example.SeatingManagement.Entity.RequestBookingRoom;
import com.example.SeatingManagement.Entity.User;
import com.example.SeatingManagement.EntityRequestBody.RequestBookingRoomDto;
import com.example.SeatingManagement.ExceptionHandling.ResourceNotFound;
import com.example.SeatingManagement.Repository.LocationRepository;
import com.example.SeatingManagement.Repository.RequestBookingRepository;
import com.example.SeatingManagement.Repository.UserRepository;
import com.example.SeatingManagement.Services.EmailService;
import com.example.SeatingManagement.Services.RequestBookingService;
import com.example.SeatingManagement.utils.EmailBody;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestBookingImple implements RequestBookingService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RequestBookingRepository requestBookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public RequestBookingRoomDto createNewRequest(RequestBookingRoomDto requestBookingRoomDto) {
        RequestBookingRoom req=this.modelMapper.map(requestBookingRoomDto,RequestBookingRoom.class);
        RequestBookingRoom newRequest=this.requestBookingRepository.save(req);
        User user = this.userRepository.findByEmail(newRequest.getEmail()).orElseThrow(()->new ResourceNotFound("User", "email", newRequest.getEmail()));
        EmailBody emailBody = new EmailBody();
        emailBody.setToEmail(user.getEmail());
        emailBody.setSubject("Confirmation of "+newRequest.getRoomType()+" Request");
        emailBody.setMessage("Dear "+user.getFirstName()+",\n" +
                "\n" +
                "This email is to confirm that we have received your request for the board room reservation on "+newRequest.getDate()+" from "+newRequest.getFromTime()+" to "+newRequest.getToTime()+" at our "+user.getLocation().getName()+" office.\n" +
                "\n" +
                "The details of your booking are as follows:\n" +
                "\n" +
                "Board Room Capacity: "+newRequest.getCapacity()+"\n" +
                "Location: "+user.getLocation().getName()+"\n" +
                "Date: "+newRequest.getDate()+"\n" +
                "Time Slot: "+newRequest.getFromTime()+" to "+newRequest.getToTime()+"\n" +
                "\n" +
                "We will send a confirmation email once the reservation has been approved. If you need to make any changes or cancel your reservation, please contact us as soon as possible.\n" +
                "\n" +
                "Thank you for your request.\n" +
                "\n" +
                "Best regards,\n" +
                "Accolite Digital");
        this.emailService.sendMail(emailBody);
        return this.modelMapper.map(newRequest,RequestBookingRoomDto.class);
    }

    @Override
    public String setActiveStatus(Integer id, boolean value) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        request.setActive(value);
        this.requestBookingRepository.save(request);
        if(!value){
            User user = this.userRepository.findByEmail(request.getEmail()).orElseThrow(()->new ResourceNotFound("User", "email", request.getEmail()));
            EmailBody emailBody = new EmailBody();
            emailBody.setToEmail(user.getEmail());
            emailBody.setSubject(request.getRoomType()+" Request Rejected");
            emailBody.setMessage("Dear "+user.getFirstName()+",\n" +
                    "\n" +
                    "I am writing to inform you that your request for the board room on "+request.getDate()+" from "+request.getFromTime()+" to "+request.getToTime()+" at our "+user.getLocation().getName()+" office has been rejected.\n" +
                    "\n" +
                    "We apologize for any inconvenience this may have caused and encourage you to submit a new request for an alternative time and date.\n" +
                    "\n" +
                    "Thank you for your understanding.\n" +
                    "\n" +
                    "Best regards,\n" +
                    "Accolite Digital");
            this.emailService.sendMail(emailBody);
        }
        return "request with "+id+"'s active status changed to "+value;
    }

    @Override
    public String setAccepted(Integer id, boolean value) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        request.setAccepted(value);
        this.requestBookingRepository.save(request);
        return "your request have been accepted and yoy are allotted a room";
    }

    @Override
    public List<RequestBookingRoomDto> allRequest() {
        List<RequestBookingRoom> requestBookingRooms=this.requestBookingRepository.findByIsActiveIsNotAccepted(true);
        List<RequestBookingRoomDto> requests=requestBookingRooms.stream().map((e)->this.modelMapper.map(e,RequestBookingRoomDto.class)).collect(Collectors.toList());
        return requests;
    }

    @Override
    public RequestBookingRoomDto getAllRequestById(Integer id) {
        RequestBookingRoom request=this.requestBookingRepository.findById(id).orElseThrow(()->new ResourceNotFound("Request","request_id",""+id));
        RequestBookingRoomDto requestBookingRoomDto=this.modelMapper.map(request,RequestBookingRoomDto.class);
        return requestBookingRoomDto;

    }

    @Override
    public List<RequestBookingRoomDto> allRequestByUser(String email) {
        List<RequestBookingRoom> requestBookingRooms=this.requestBookingRepository.findByEmailAndIsActiveAndIsNotAccepted(email,true);
        List<RequestBookingRoomDto> requests=requestBookingRooms.stream().map((e)->this.modelMapper.map(e,RequestBookingRoomDto.class)).collect(Collectors.toList());
        return requests;
    }
}
