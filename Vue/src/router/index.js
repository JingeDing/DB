import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import queryStudent from "../views/queryStudent";
import deleteCourse from "../views/studentView/deleteCourse";
import Index from '../views/Index';
import Login from '../views/Login';
import studentIndex from "@/views/studentView/studentIndex";
import studentHome from "@/views/studentView/studentHome";
import courseHome from "@/views/course/courseHome";
import departmentHome from "@/views/department/departmentHome";
import classHome from "@/views/class/classHome";
import teacherHome from "@/views/teacher/teacherHome";
import studentSearchTeacher from "@/views/studentView/studentSearchTeacher";
import studentInfo from "@/views/studentView/studentInfo";
import selectCourse from "@/views/studentView/selectCourse";
import studentCourseTable from "@/views/studentView/studentCourseTable";
import selectMark from "@/views/studentView/selectMark";
import selectByCno from "@/views/statistical/selectByCno";
import selectMarkBySno from "@/views/statistical/selectMarkBySno";
import selectMarkByNo from "@/views/statistical/selectMarkByNo";
import addGrade from "@/views/addGrade";

const routes = [
  {
    path:'/',
    name:'Login',
    component:Login,
    meta: {
      requiresAuth:false,
      roles:['admin','user']
    }
  },
  {
    path:'/Home',
    redirect:'/view',
    name:'home',
    component:Index,
    meta: {
      requiresAuth:true,
      roles:['admin']
    },
    children: [
      {
        path: '/view',
        name: 'view',
        component: Home,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      }
    ]
  },
  {
    path:'/studentHome',
    redirect:'/studentView',
    name:'studentHome',
    component:studentIndex,
    meta: {
      requiresAuth:true,
      roles:['user']
    },
    children: [
      {
        path: '/studentView',
        name: '学生首页',
        component: studentHome,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/studentInfo',
        name: '个人信息',
        component: studentInfo,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/studentSearchTeacher',
        name: '查看教师',
        component: studentSearchTeacher,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/selectCourse',
        name: '选修课程',
        component: selectCourse,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/deleteCourse',
        name: '已选/退课',
        component: deleteCourse,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/CourseTable',
        name: '课程表',
        component: studentCourseTable,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
      {
        path: '/selectMark',
        name: '查看成绩',
        component: selectMark,
        meta: {
          requiresAuth:true,
          roles:['user']
        }
      },
    ]
  },
  {
    path: '/Home',
    name: "基本信息管理",
    component: Index,
    meta: {
      requiresAuth:true,
      roles:['admin']
    },
    children:[
      {
        path:'/departmentHome',
        name:'学院信息管理',
        component:departmentHome,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path:'/classHome',
        name:'班级信息管理',
        component: classHome,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path: '/teacherHome',
        name: '教师信息管理',
        component: teacherHome,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path: '/queryStudent',
        name: '学生信息管理',
        component: queryStudent,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path: '/courseHome',
        name: '课程信息管理',
        component: courseHome,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path: '/addGrade',
        name: '录入课程成绩',
        component: addGrade,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
    ]
  },
  {
    path: '/navigation2',
    name: '信息查询统计',
    component: Index,
    meta: {
      requiresAuth:true,
      roles:['admin']
    },
    children: [
      {
        path:'/selectByCno',
        name: '查看班级学生信息',
        component: selectByCno,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path:'/selectMarkBySno',
        name: '查找某学生成绩',
        component: selectMarkBySno,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
      {
        path:'/selectMarkByNo',
        name: '查看某课程成绩',
        component: selectMarkByNo,
        meta: {
          requiresAuth:true,
          roles:['admin']
        }
      },
    ]
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL/),
  routes
})


export default router
