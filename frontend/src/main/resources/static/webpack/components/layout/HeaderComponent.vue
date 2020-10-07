<template>
  <div>
    <button v-on:click="onClickHome()">홈으로</button>
    <router-link :to="{name: 'TestList'}"><button>테스트 목록</button></router-link>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent",
  methods: {
    onClickHome: function(){
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>

</style>