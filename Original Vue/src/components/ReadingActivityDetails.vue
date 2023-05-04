<template>
  <div v-if="readingActivity.requestId === 1">
    <div id="display-ra">
      <div id="cover-ra">
        <img
          id="book-cover-ra"
          :src="this.book.coverUrl"
          alt="Book Cover URL"
        />
      </div>
      <div id="text-content-ra">
        <h4 id="book-ra">{{ this.book.title }}</h4>

        <h5 id="date-ra">date read: {{ readingActivity.dateRead }}</h5>
        <h5 id="mins-ra">minutes read: {{ readingActivity.minutesRead }}</h5>
        <h5 id="notes-ra">notes: {{ readingActivity.notes }}</h5>
      </div>
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";

export default {
  name: "reading-activity-details",
  props: ["activity"],

  data() {
    return {
      readingActivity: this.activity,
      book: {
        id: 0,
        isbn: "",
        coverUrl: "",
        title: "",
        userCreated: false,
        author: "",
      },
    };
  },

  created() {
    this.readingActivity = this.activity;
    hiveService.getBook(this.readingActivity.bookId).then((response) => {
      this.book = response.data;
    });
  },
};
</script>

<style>
#display-ra {
  display: flex;
  height: 170px;
  margin-bottom: 15px;
  justify-content: center;
  align-items: center;
  font-size: 1.2rem;
}

#text-content-ra {
  display: block;
  margin-bottom: 12px;
  margin-left: 10px;
}

#book-ra {
  margin-bottom: 0px;
  margin-top: 0px;
  border-bottom: 2px solid #63a8da;
}

#date-ra {
  margin-bottom: 0px;
  margin-top: 0px;
}

#mins-ra {
  margin-bottom: 0px;
  margin-top: 0px;
}

#notes-ra {
  margin-bottom: 0px;
  margin-top: 0px;
}

#cover-ra {
  width: 100px;
}
#book-cover-ra {
  width: 100%;
}
</style>