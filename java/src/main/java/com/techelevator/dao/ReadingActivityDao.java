package com.techelevator.dao;


import com.techelevator.model.ReadingActivity;

import java.util.List;

public interface ReadingActivityDao {

     Integer addReadingActivity(ReadingActivity read);

     List<ReadingActivity> allActivityByMemId(int memberId);

     ReadingActivity editActivity(ReadingActivity read);

     Boolean deleteActivity(int readingId);

     List<ReadingActivity> pendingReading(int userId);

}
