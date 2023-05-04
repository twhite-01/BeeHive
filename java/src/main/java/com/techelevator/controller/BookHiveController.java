package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.JSON.BookJSON;
import com.techelevator.JSON.ImageLinks;
import com.techelevator.JSON.Root;
import com.techelevator.JSON.VolumeInfo;
import com.techelevator.dao.*;
import com.techelevator.model.*;


import com.techelevator.services.GoogleAPIRest;
import com.techelevator.services.HiveService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class BookHiveController {

    private UserDao userDao;
    private MemberDao memberDao;
    private HiveService hiveService;
    private BookDao bookDao;
    private Principal principal;
    private GoogleAPIRest google;
    private RestTemplate restTemplate;
    private ReadingActivityDao readDao;
    private PrizeDao prizeDao;




    //Note: Removed ImageLinks from parameters

    public BookHiveController(MemberDao memberDao, UserDao userDao, HiveService hiveService, BookDao bookDao, ReadingActivityDao readDao, PrizeDao prizeDao, GoogleAPIRest google) {
        this.memberDao = memberDao;
        this.userDao = userDao;
        this.hiveService = hiveService;
        this.bookDao = bookDao;
        this.readDao = readDao;
        this.prizeDao = prizeDao;
        this.google = google;

    }


    //Members
    @GetMapping("/hive/{id}")
    public Member getMemberById(@PathVariable int id) {

        return hiveService.getMemberById(id);
    }

    @GetMapping("/hive")
    public List<Member> getAllMembers(Principal principal) {
        return hiveService.getAllMembers(principal);
    }


        @PostMapping("/hive")
    public Integer addMember(@RequestBody Member member, Principal principal) {
        return hiveService.insertUserIdToNewMember(member, principal);
    }

    @PutMapping("/hive/{memberId}")
    public Member editMember(@RequestBody Member member, @PathVariable int memberId) {
        return hiveService.editMember(member,memberId);
    }

    @DeleteMapping("hive/{memberId}")
    public Boolean deleteMember(@PathVariable int memberId) {
        return hiveService.deleteMember(memberId);
    }

    @GetMapping("/hive/{memberId}/minutes")
    public MinutesReadDto getTotalMinutesByMember(@PathVariable int memberId) {
        return hiveService.getTotalMinutesByMember(memberId);
    }


    //Book

    @GetMapping("hive/{memberId}/books")
    public List<Book> allBooksByMember(@PathVariable int memberId) {
        return bookDao.userBookList(memberId);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookDao.getBookByID(id);
    }

    @PostMapping("/book")
    public Integer addBook(@RequestBody Book book) {
        return bookDao.addBook(book);
    }

    @GetMapping("/hive/{memberId}/books/most-recent")
    public List<Integer> getRecentBooks(@PathVariable int memberId) {
        return bookDao.getRecentBooks(memberId);
    }

    @PostMapping("/hive/member-book")
    public Boolean handleMemberBookTable(@RequestBody MemberBookDto memberBookDto){
        return hiveService.postOrPutMemberBook(memberBookDto);
    }


    //Activity

    @PutMapping("/hive/{memberId}/activity")
    public ReadingActivity editActivity(@RequestBody ReadingActivity read) {
        return hiveService.editActivity(read);
    }

    @PostMapping("/hive/{memberId}/activity")
    public Integer addReadingActivity(@PathVariable int memberId, @RequestBody ReadingActivity read) {
        return hiveService.addReadingActivity(read);
    }

    @GetMapping("/hive/{memberId}/activity")
    public List<ReadingActivity> getAllReadsByMemId(@PathVariable int memberId) {
        return hiveService.getAllReadsByMemId(memberId);
    }

    @DeleteMapping("/hive/{memberId}/activity/{activityId}")
    public Boolean deleteActivity(@PathVariable int activityId) {
        return hiveService.deleteActivity(activityId);
    }

    @GetMapping("/hive/approval")
    public List<ReadingActivity> pendingRequest(Principal principal){
        return hiveService.pendingRequest(principal);
    }


    //Prize

    @GetMapping("/hive/prize/{prizeId}")
    public Prize getPrizeByPrizeId(@PathVariable int prizeId) {
        return hiveService.getPrizeByPrizeId(prizeId);
    }

    @GetMapping("/hive/prize/member/{memberId}")
    public List<Prize> getPrizesByMemberId(@PathVariable int memberId) {
        return hiveService.getPrizesByMemberId(memberId);
    }

    @GetMapping("/hive/prize/user")
    public List<Prize> getPrizesById(Principal principal) {
        return hiveService.getPrizesById(principal);
    }

    @PostMapping("/hive/prize")
    public Integer addPrize(@RequestBody Prize prize) {
        return hiveService.addPrize(prize);

    }

    @DeleteMapping("/hive/prize/{prizeId}")
    public boolean deletePrize(@PathVariable int prizeId) {
        return hiveService.deletePrize(prizeId);
    }



    @GetMapping("/hive/{memberId}/prize/{prizeId}")
    public MinutesReadDto getMinutesTowardPrize(@PathVariable int memberId, @PathVariable int prizeId) {
        return hiveService.getMinutesTowardPrize(memberId, prizeId);
    }

    @PutMapping("/hive/memberprize")
    public Boolean handleMemberPrizeTable(@RequestBody MemberPrizeDto memberPrizeDto){
        return hiveService.postOrPutMemberPrize(memberPrizeDto);
    }

    @GetMapping("/prize/{prizeId}/members")
    public List<Member> getAllMembersForPrizeId(@PathVariable int prizeId){
        return memberDao.getAllMembersForAPrizeId(prizeId);
    }

    //Family

    @PatchMapping("/hive/family-name/{id}")
    public boolean addFamilyName(@RequestBody FamilyNameDto famName, @PathVariable int id){
        return userDao.updateFamilyName(famName);
    }



    //google

    @GetMapping("/google/books/{searchText}")
    public List<BookJSON> get5books(@PathVariable String searchText){
        Root newRoot = google.get5BooksByTitle(searchText);
        return google.googleSearchResultsByTitle(newRoot);
    }

    @GetMapping("/google/book/{searchText}")
    public BookJSON getBookByISBN(@PathVariable String searchText){
        Root newRoot = google.getByIsbn(searchText);
        return google.googleSearchResultsByISBN(newRoot, searchText);
    }
}




