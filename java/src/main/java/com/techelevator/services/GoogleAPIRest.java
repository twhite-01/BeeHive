package com.techelevator.services;


import com.techelevator.JSON.BookJSON;
import com.techelevator.JSON.Item;
import com.techelevator.JSON.Root;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleAPIRest {

    RestTemplate restTemplate = new RestTemplate();
//TITLE
    public Root get5BooksByTitle(String searchText) {

        String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=intitle:";

        String endUrl = "&maxResults=5&key=AIzaSyBtUzMJUU4PaZdT7Bjkz_z9jzl_KKzvTAE";

        String searchUrl = baseUrl + searchText + endUrl;

        Root results = restTemplate.getForObject(searchUrl, Root.class);

        return results;
    }

    // ISBN

    public Root getByIsbn(String searchText) {

        String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

        String endUrl = "&maxResults=5&key=AIzaSyBtUzMJUU4PaZdT7Bjkz_z9jzl_KKzvTAE";

        String searchUrl = baseUrl + searchText + endUrl;

        Root results = restTemplate.getForObject(searchUrl, Root.class);

        return results;
    }

    public List<BookJSON> googleSearchResultsByTitle(Root root) {

        List<Item> items = root.getItems();
        List<BookJSON> books = new ArrayList<>();

        for (Item book : items) {

            String ISBN = "";
            String author = "";
            String coverUrl = "";

            String title = book.getVolumeInfo().getTitle();
            try {
                 author = book.getVolumeInfo().getAuthors().get(0);
            }catch(Exception e){
                author = "Author Unavailable";
            }
            try {
                 coverUrl = book.getVolumeInfo().getImageLinks().getSmallThumbnail();
            }catch(Exception e){
                coverUrl = "";
            }
            try {
                if (book.getVolumeInfo().getIndustryIdentifiers().get(0) != null) {
                    ISBN = book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier();
                }
            }catch(Exception e) {
                ISBN = "";
            }

            BookJSON book1 = new BookJSON(title, author, ISBN, coverUrl);

            books.add(book1);
        }return books;
    }

    public BookJSON googleSearchResultsByISBN(Root root, String searchText) {

            String ISBN = "";
            String author = "";
            String coverUrl = "";

            String title = root.getItems().get(0).getVolumeInfo().getTitle();
            try {
                author = root.getItems().get(0).getVolumeInfo().getAuthors().get(0);
            }catch(Exception e){
                author = "Author Unavailable";
            }
            try {
                coverUrl = root.getItems().get(0).getVolumeInfo().getImageLinks().getSmallThumbnail();
            }catch(Exception e){
                coverUrl = "";
            }
            try {
                ISBN = searchText;
            }catch(Exception e) {
                ISBN = "";
            }

            BookJSON book = new BookJSON(title, author, ISBN, coverUrl);
        return book;

    }

    /*
  QUERY PROPERTIES

  Examples: GET https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey

    intitle: Returns results where the text following this keyword is found in the title.
    inauthor: Returns results where the text following this keyword is found in the author.
    inpublisher: Returns results where the text following this keyword is found in the publisher.
    subject: Returns results where the text following this keyword is listed in the category list of the volume.
    isbn: Returns results where the text following this keyword is the ISBN number.
    lccn: Returns results where the text following this keyword is the Library of Congress Control Number.
    oclc: Returns results where the text following this keyword is the Online Computer Library Center number*//*


}
*/

}



