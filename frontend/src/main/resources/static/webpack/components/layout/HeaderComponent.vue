<template>
  <div>
    이게 바로 상단 메뉴
    <button v-on:click="onClickHome()">홈으로</button>
    <button v-on:click="onClickTestList()">테스트 목록</button>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent",
  methods: {
    onClickHome: function(){
      this.$router.push('/')
    },
    onClickTestList: function (){
      this.$router.push('/test/list')
    }
  }
}
</script>

<style scoped>

</style>