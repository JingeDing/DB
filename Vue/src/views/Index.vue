<template>
      <Header/>
      <div style="display: flex">
        <Aside/>
        <router-view style="flex: 1"></router-view>
      </div>

</template>



<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
export default {
  name: "index",
  components: {Aside, Header},
  beforeRouteEnter:(to,from,next)=>{
    let name = sessionStorage.getItem("username");
    if(name==='admin'){
      next();
    }else {
      next({
        path:"/"
      })
    }
  }
}
</script>

<style scoped>

</style>