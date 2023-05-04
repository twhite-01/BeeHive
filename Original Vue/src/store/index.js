import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
//import HiveService from '../services/HiveService'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store(
  {
    state: {
      token: currentToken || '',
      user: currentUser || {},
      storeBookId: 0,
      hiveMembers: [],
      parentPageView: 0,
      childPageView: 0,
      requestList: [],
      threeDisplayBooks: [],
      allPrizes: [],
      readingActivityList: [],
      showDisplay: true,
      parentPathParam: 0,
      currentMember: 0,
      currentPrizeId: 0,
      readMinutes: 0,
      memberBookList: [],
      thisPrizeCurrentGoalMinutes: 0
    },


    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
        state.parentPageView = 0;
      },
      SET_BOOK(state, bookId) {
        state.storeBookId = bookId;
      },
      SET_HIVE_MEMBERS(state, members) {
        state.hiveMembers = members;
      },
      SET_MINUTES_READ(state, memberId) {
        axios.get(`/hive/${memberId}/minutes`).then((response) => {
          state.readMinutes = response.data;
        })
      },
      INCREMENT_PARENT_VIEW(state) {

        state.parentPageView++;

      },
      DECREMENT_PARENT_VIEW(state) {

        state.parentPageView--;

      },

      INCREMENT_CHILD_VIEW(state) {

        state.childPageView++;

      },
      DECREMENT_CHILD_VIEW(state) {

        state.childPageView--;

      },
      JUMP_TO_CHILD_VIEW(state, pageView) {
        state.childPageView = pageView;
      },

      RESET_CHILD_VIEW(state) {
        state.childPageView = 0;
      },

      RESET_PARENT_VIEW(state) {
        state.parentPageView = 0;
      },

      JUMP_TO_PARENT_VIEW(state, pageView) {
        state.parentPageView = pageView;
      },

      SET_REQUEST_LIST(state, reqList) {
        state.requestList = reqList;
      },

      SET_BOOK_DISPLAY(state, books) {
        state.threeDisplayBooks = books;
      },

      SET_READING_LIST(state, newList) {
        state.readingActivityList = newList;
      },


      SET_SHOWDISPLAY_BOOL(state) {

        state.showDisplay = !state.showDisplay;
      },

      SET_CURRENT_PARENT(state, parentId) {

        state.parentPathParam = parentId;

      },

      SET_CURRENT_MEMBER(state, memberId) {

        axios.get(`/hive/${memberId}`).then((response) => {
          state.currentMember = response.data;
        })

      },

      RESET_CURRENT_MEMBER(state) {
        state.currentMember = [];
      },

      SET_MEMBER_BOOKS(state, memberId) {
        axios.get(`/hive/${memberId}/books`).then((response) => {
          state.memberBookList = response.data;
        });

      }, 

      SET_PRIZELIST(state){
        axios.get(`/hive/prize/user`).then((response) =>{
          state.allPrizes = response.data;
        });
      },

      // SET_PRIZE_GOAL_MINUTES(state, int){
      //   axios.get(``)
      //   state.thisPrizeCurrentGoalMinutes = int;
      // }

    }
  })
