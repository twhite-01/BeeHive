<template>
  <div id="book-result">
    <div id="cover-art">
      <img :src="book.coverUrl" alt="book cover" id="book-pic" />
    </div>

    <div id="title-author">
      <h2 id="title-of-book">{{ book.title }}</h2>
      <h3 id="author-name">{{ book.author }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-result",
  props: ["bookResult"],
  data() {
    return {
      book: this.bookResult,
    };
  },
};
</script>

<style>
#book-result:hover {
  background-color: rgb(200, 204, 204);
}
#book-result {
  display: flex;
  padding: 15px;
  height: 100px;
  width: 475px;
  border: 2px black solid;
  margin-bottom: 10px;
  margin-top: 10px;
  background: rgba(255, 255, 253, 0.932);
}

#cover-art {
  height: 100%;
}

#book-pic {
  height: 100%;
}

#title-author {
  /* text-align: center;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding-left: 15px; */

  margin: 10px 0 10px 0;
  padding-left: 15px;
}

h2,
h3 {
  font-size: 1rem;
  margin-left: 10px;
  margin-bottom: 0;
}
</style>