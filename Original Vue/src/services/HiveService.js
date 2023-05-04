import axios from 'axios';

export default {
//member paths
  getMember(id) {
    return axios.get(`/hive/${id}`)
  },

  getHiveMembers(){
    return axios.get(`/hive`)
  },

  addHiveMember(member){
    return axios.post(`/hive`, member)
  },

  updateMember(member){
    return axios.put(`/hive/${member.id}`, member)
  },


  // Book Paths
  addNewBook(book){
    return axios.post(`/book`, book)
  },

  getBook(id){
    return axios.get(`/book/${id}`)
  },


// Activity 

  addActivity(memberId, activity){
    return axios.post(`/hive/${memberId}/activity`, activity)
  },

  getReadsByMember(memberId){
    return axios.get(`/hive/${memberId}/activity`) 
  },

  //Prizes
  addPrize(prize){
   return axios.post(`/hive/prize/`, prize)
  }, 

  getPrizesByMember(memberId){
    return axios.get(`/hive/prize/member/${memberId}`)
  },

  getPrizeByPrizeId(prizeId){
    return axios.get(`/hive/prize/${prizeId}`)
  },

  getPrizesByUser(){
    return axios.get(`/hive/prize/user`)
  },

  // getMinutesTowardPrize(memberId, prizeId){
  //   return axios.get(`/hive/${memberId}/prize/${prizeId}`)
  // },

  addHiveName(userId, hiveName){
    return axios.patch(`/hive/family-name/${userId}`, hiveName)
  },

  getTotalMinutesByMember(memberId){
    return axios.get(`/hive/${memberId}/minutes`)
  }, 

  getMostRecentBooks(memberId){
    return axios.get(`/hive/${memberId}/books/most-recent`)
  },

  getPendingRequestsByUserId(){
    return axios.get(`/hive/approval/`)
  },

  updatePendingActivity(memberId, activity){
    return axios.put(`/hive/${memberId}/activity`, activity)
  },

  getAllBooksForMember(memberId){
    return axios.get(`/hive/${memberId}/books`)
  },
  
  searchGoogleBooks(searchText){
    return axios.get(`/google/books/${searchText}`)
  }, 

  searchGoogleIsbns(searchIsbn){
    return axios.get(`/google/book/${searchIsbn}`)
  },

  updateMemberBookDto(memberBook){
    return axios.post(`/hive/member-book` , memberBook)
  },

  getAllMembersForAGivenPrize(prizeId){
    return axios.get(`/prize/${prizeId}/members`)
  }
 
 

}