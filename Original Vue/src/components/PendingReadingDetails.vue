<template>
  <div id="pending-container">
    <div id="pending-request-display">
      <span id="mem-date"
        ><span id="one-line"
          ><p id="mini-header">request from:</p>
          <p id="member-from">
            {{ this.member.firstName }} {{ this.member.lastInitial }}
          </p></span
        >
        <p id="date-read">{{ activity.dateRead }}</p></span
      >
      <span id="one-line"
        ><p id="mini-header">book:</p>
        <p id="book-read">{{ this.book.title }}</p></span
      >
      <span id="one-line">
        <p id="mini-header">minutes read:</p>
        <p id="mins-read">{{ activity.minutesRead }}</p></span
      >
      <span id="one-line"
        ><p id="mini-header">notes:</p>
        <p id="pending-notes">{{ activity.notes }}</p></span
      >
      <div id="approve-reject">
        <button id="approved" @click="updateApprovalStatus(1)">approve</button>
        <button id="rejected" @click="updateApprovalStatus(3)">
          don't approve
        </button>
      </div>
    </div>
  </div>
</template>

<script>

import hiveService from '../services/HiveService.js'

export default {
    name: 'pending-reading-details',
    props: ['pendingActivity'],

    data(){
        return {
            activity: this.pendingActivity,
            member: {},
            book: {}
        }
    },

    created(){

        this.activity = this.pendingActivity;
    

    },

    mounted(){
        hiveService.getMember(this.activity.memberId)
        .then((response) => {
            this.member = response.data;
        }); 

         hiveService.getBook(this.activity.bookId)
         .then((response) => {
             this.book = response.data
         })

    },

    methods: {
        updateApprovalStatus(status){
            this.activity.requestId = status;
            hiveService.updatePendingActivity(this.activity.memberId, this.activity)
            .then((response)=>{
                if(response.status == 200){
                    hiveService.getPendingRequestsByUserId().then((response) =>{
                        this.$store.commit('SET_REQUEST_LIST', response.data)
                    })
                }
            })
            
        }
    }
    
 
}
</script>

<style>
#pending-container {
  margin: 0 auto;
}

#pending-request-display {
  display: flex;
  flex-direction: column;
  width: 420px;
  padding: 10px;
  margin-bottom: 15px;
  font-family: "Livvic", sans-serif;
  background: rgb(255, 254, 249);
  border: 10px rgb(0, 0, 0) ridge;
  border-radius: 10px;
}

#mem-date {
  display: flex;
  justify-content: space-between;
  border-bottom: 2px #63a8da solid;
}

#one-line {
  display: flex;
}

#mini-header {
  font-weight: bold;
  margin-right: 5px;
}

#approve-reject {
  display: flex;
  justify-content: flex-end;
}

#approved {
  margin: 10px;

  border-radius: 20px;
  border: 2px #63a8da solid;
}

#rejected {
  margin: 10px;
  border-radius: 20px;
  border: 2px #63a8da solid;
}
</style>