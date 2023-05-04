import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import MemberInfo from '../views/MemberInfo.vue'
import HiveMembers from '../views/HiveMembers.vue'
import AddMember from '../views/AddMember.vue'
import AddBook from '../views/AddBook.vue'
import MyBooks from '../views/MyBooks.vue'
import ParentHome from '../views/ParentHome.vue'
import ChildHome from '../views/ChildHome.vue'
import UpdateMembers from '../views/UpdateMembers.vue'
import AddReadingActivity from '../views/AddReadingActivity.vue'
import AddPrize from '../views/AddPrize.vue'
import PinValidation from '../views/PinValidation.vue'
import CheckRequests from '../views/CheckRequests.vue'
import ParentChildHome from '../views/ParentChildHome.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/hive",
      name: "hive-members",
      component: HiveMembers,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/hive/add-member",
      name: "add-member",
      component: AddMember,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/book/my-books",
      name: "my-books",
      component: MyBooks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/parent-home/:id",
      name: "parent-home",
      component: ParentHome,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/parent-home/:id/child/:childId",
      name: "parent-child-home",
      component: ParentChildHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/child-home/:id",
      name: "child-home",
      component: ChildHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/hive/update-members",
      name: "update-members",
      component: UpdateMembers,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/hive/update-members/:id",
      name: "member-info",
      component: MemberInfo,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/parent-home/:id/activity/",
      name: "add-reading-activity",
      component: AddReadingActivity,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/parent-home/:id/add-book",
      name: "add-book",
      component: AddBook,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/parent-home/:id/add-prize",
      name: "add-prize",
      component: AddPrize,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/pin-validation/:id",
      name: "pin-validation",
      component: PinValidation,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/hive/:id/check-requests",
      name: "check-requests",
      component: CheckRequests,
      meta: {
        requiresAuth: true
      }
    },






  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
