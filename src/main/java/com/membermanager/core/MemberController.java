package com.membermanager.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MemberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = {"/ping", "/Ping"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity ping()
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Testing the endpoint");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("PONG");

    }

    @RequestMapping(value = {"/member", "/Member"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllMembers()
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to get all members");
        List<Member> memberList = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberList);

    }

    @RequestMapping(value = {"/member/{firstName}", "/Member/{firstName}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getMember(@PathVariable String firstName)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to get the member by first name");
        if (firstName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<Member> member = memberService.getMember(firstName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
    }

    @RequestMapping(value = {"/member", "/Member"}, method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity addNewMember(@RequestBody Member member)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to add a new member in repository");
        if (member == null || StringUtils.isEmpty(member.getFirstName()) || StringUtils.isEmpty(member.getLastName())
                || member.getDateOfBirth() == null || member.getZipCode() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(member);
        }
        memberService.addNewMember(member);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @RequestMapping(value = {"/member", "/Member"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteMember(@RequestBody Member member)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to delete a member");
        if (member == null || StringUtils.isEmpty(member.getFirstName()) || StringUtils.isEmpty(member.getLastName())
                || member.getDateOfBirth() == null || member.getZipCode() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(member);
        }
        memberService.removeMember(member);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
    }

    @RequestMapping(value = {"/member", "/Member"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateMember(@RequestBody Member member)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to delete a member");
        if (member == null || StringUtils.isEmpty(member.getFirstName()) || StringUtils.isEmpty(member.getLastName())
                || member.getDateOfBirth() == null || member.getZipCode() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(member);
        }

        int updatedRows = memberService.updateMember(member);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(member);
        }
    }


}