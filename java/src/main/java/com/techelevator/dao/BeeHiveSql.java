package com.techelevator.dao;

public enum BeeHiveSql {


 //---------------------------- USERS----------------------------------------------
    GET_USER_BY_ID
    (
"SELECT user_id, username,password_hash, role,family_name" +
        "FROM users " +
        "WHERE user_id = ?"
    ),
    GET_ALL_USERS
    (
"SELECT user_id, username,password_hash, role,family_name " +
        "FROM users"
    ),
    UPDATE_FAMILY_NAME
    (
"UPDATE users " +
        "SET family_name = ? " +
        "WHERE user_id = ?"
    ),
    INSERT_USER_FOR_CREATION
    (
"INSERT INTO users (username,password_hash,role) " +
         "VALUES (?,?,?)"
    ),

    //---------------------------- BOOKS ----------------------------------------------

    GET_BOOK_BY_ID
    (
"SELECT id, isbn, cover_url, title, description, user_created, author " +
        "FROM book " +
        "WHERE id = ? "
    ),

    GET_BOOK_BY_USER_ID
    (
"SELECT id, isbn, cover_url, title, description, user_created, author " +
        "FROM book " +
        "WHERE id = ? "
    ),
    DELETE_BOOK
    (
"DELETE FROM book " +
        "WHERE id = ? "
    ),

    ADD_BOOK
    (
"INSERT INTO book(isbn,cover_url,title,author) " +
        "VALUES(?,?,?,?) RETURNING id "
    ),

    GET_RECENT_BOOKS
    (
"SELECT DISTINCT book_id, MAX(date_read) AS most_recent " +
        "FROM reading_activity " +
        "WHERE member_id = ? " +
        "GROUP BY book_id " +
        "ORDER BY most_recent DESC " +
        "LIMIT 3 "
    ),


    USER_BOOKS
    (
"SELECT b.id, b.isbn, b.cover_url,  b.title, b.author, mb.is_completed " +
        "FROM book b " +
        "INNER JOIN member_book mb " +
            "ON b.id=mb.book_id " +
        "WHERE mb.member_id = ? "
    ),

    ADD_MEMBER_BOOK_TABLE
    (
"INSERT INTO member_book" +
        "(member_id, book_id, is_completed) " +
        "VALUES ( ?, ?, ?);"
    ),

    CHECK_FOR_ROW_EXISTS
    (
"SELECT member_id " +
        "FROM member_book " +
        "WHERE member_id = ? AND book_id = ? "
    ),

    EDIT_MEMBER_BOOK_COMPLETED
    (
"UPDATE member_book " +
        "SET member_id= ?, book_id=?, is_completed= ? " +
        "WHERE member_id = ? AND book_id = ? "
    ),

    //---------------------------- MEMBERS ----------------------------------------------

    GET_MEMBER_BY_ID
    (
    "SELECT id, is_child, user_id, first_name, last_initial, avatar_id, pin " +
            "FROM members " +
            "WHERE id = ?"
    ),

    GET_MEMBERS_BY_ID
    (
"SELECT id, is_child, user_id, first_name, last_initial, avatar_id, pin " +
        "FROM members " +
        "WHERE user_id = ?"
    ),

    ADD_MEMBER
    (
"INSERT INTO members(is_child, user_id, first_name, last_initial, avatar_id, pin) " +
        "VALUES (?, ?, ?, ?, ?, ?) RETURNING id;"
    ),

    DELETE_MEMBER_READING_ACTIVITY
    (
" DELETE FROM reading_activity " +
        "WHERE member_id = ? "
    ),

    DELETE_MEMBER_BOOK
    (
"DELETE FROM  member_book " +
        "WHERE member_id = ? "
    ),

    DELETE_MEMBER_PRIZE_TABLE
    (
"DELETE FROM member_prize " +
        "WHERE member_id = ? "
    ),

    DELETE_MEMBER
    (
"DELETE FROM members " +
         "WHERE id = ? "
    ),

   EDIT_MEMBER
  (
"UPDATE members " +
      "SET id=?, is_child=?, user_id=?, first_name=?, last_initial=?, avatar_id=?, pin=? " +
      "WHERE id = ? ; "
  ),

   MEMBER_TOTAL_MINUTES_READ
  (
"SELECT SUM(minutes_read) as total_mins " +
         "FROM reading_activity " +
         "WHERE member_id = ? AND request_status_id = 1 "
  ),

   MEMBERS_ASSIGNED_TO_PRIZE
   (
"SELECT m.id, m.first_name, m.last_initial, m.is_child, m.user_id, m.avatar_id, m.pin " +
        "FROM members m " +
        "INNER JOIN member_prize mp " +
        "ON m.id = mp.member_id " +
        "WHERE mp.prize_id = ? "
   ),

   //---------------------------- Prize ----------------------------------------------

    GET_PRIZE_BY_ID
    (
"SELECT id,prize_name,description,goal_minutes,user_id,max_winners,current_winners,start_date, " +
        "end_date,is_active " +
        "FROM prize " +
        "WHERE id = ? "
    ),

    GET_PRIZE_BY_MEMBER_ID
(
"SELECT id,prize_name,description,goal_minutes,user_id,max_winners,current_winners,start_date,end_date,is_active " +
        "FROM prize p "+
        "INNER JOIN member_prize mp " +
            "ON p.id = mp.prize_id " +
        "WHERE mp.member_id = ? "

    ),
    GET_PRIZES_BY_USER
    (
"SELECT id,prize_name,description,goal_minutes,user_id,max_winners,current_winners,start_date, " +
        "end_date,is_active " +
        "FROM prize " +
        "WHERE user_id = ? "
    ),
    CHECK_PRIZE_EXISTS
    (
"SELECT member_id " +
        "FROM member_prize " +
        "WHERE member_id = ? AND prize_id = ? "
    ),
    EDIT_MEMBER_PRIZE_COMPLETED
    (
"INSERT INTO member_prize(member_id, prize_id, prize_status_id) " +
        "VALUES (?, ?, ?);"
    ),
    PARENT_EDIT_PRIZE
    (
"SELECT * FROM members m " +
        "INNER JOIN member_prize mp " +
                "ON m.id = mp.member_id " +
        "WHERE mp.prize_id = ?"
    ),
    ADD_PRIZE
    (
"INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, "+
        "end_date, is_active) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;"
    ),

    DELETE_PRIZE
    (
"UPDATE prize" +
        "SET id=?, prize_name=?, description=?, goal_minutes=?, user_id=?, max_winners=?, current_winners=?, " +
        "start_date=?, end_date=?, is_active=? " +
        "WHERE id = ?;"
    ),

    GET_MINUTES_TOWARD_PRIZE
    (
"SELECT SUM(minutes_read) AS total_mins " +
        "FROM reading_activity ra " +
        "INNER JOIN member_prize mp ON mp.member_id = ra.member_id " +
        "INNER JOIN prize p ON p.id = mp.prize_id " +
        "WHERE ra.date_read >= p.start_date AND ra.date_read <= p.end_date AND p.is_active = true " +
        "AND mp.prize_status_id = 2 AND ra.member_id = ? AND p.id = ?"
    ),
    ADD_TO_MEMBER_PRIZE_TABLE
    (
"INSERT INTO member_prize (member_id, prize_id, prize_status_id) " +
        "VALUES (?, ?, ?)"
    ),
    CHECK_FOR_PRIZE
    (
"SELECT id " +
        "FROM prize where id = ? "
    ),

    //---------------------------- READING ACTIVITY----------------------------------------------
    ADD_READING_ACTIVITY
    (
"INSERT INTO reading_activity( member_id, book_id, minutes_read, notes, format_id, " +
        "request_status_id, date_read) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id; "
    ),
    MEMBER_FULL_ACTIVITY_LIST
    (
"SELECT * " +
        "FROM reading_activity " +
        "WHERE member_id = ? "
    ),
    EDIT_ACTIVITY
    (
"UPDATE reading_activity" +
        "SET member_id=?, book_id=?, minutes_read=?, notes=?, format_id=?, request_status_id=?, date_read=? " +
        "WHERE id = ?; "
    ),
    DELETE_ACTIVITY
    (
"DELETE FROM reading_activity " +
        "WHERE id = ? "
    ),
    PENDING_READING_LIST
    (
"SELECT ra.id, ra.member_id, ra.book_id, ra.minutes_read, ra.notes, ra.format_id, ra.request_status_id, ra.date_read FROM reading_activity ra " +
        "INNER JOIN members me ON ra.member_id = me.id " +
        "WHERE user_id = ? AND request_status_id = 2"
    );


    private final String sqlString;

    BeeHiveSql(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }


}
