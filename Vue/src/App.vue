<template>

  <div id="app">
    <router-view  v-if="isShow"></router-view>
  </div>
</template>
<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
export default {
  name:"App",
  components: { Aside, Header},
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isShow: true
    }
  },
  methods: {
    reload () {
      this.isShow= false
      this.$nextTick(function () {
        this.isShow= true
      })
    }
  }
}
</script>