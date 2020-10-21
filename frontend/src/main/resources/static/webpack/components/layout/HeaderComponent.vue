<template>
  <div class="navbar-nav bg-gray-600 sidebar-dark accordion">
    <table>
      <thead>
        <th>
          <router-link :to="{name: 'Home'}" class="nav-item"><a class="nav-link"><span>홈으로</span></a></router-link>
        </th>
        <th>
          <router-link :to="{name: 'TestList'}" class="nav-item"><a class="nav-link"><span>테스트</span></a></router-link>
        </th>
        <th>
          <router-link :to="{name: 'TestcaseList'}" class="nav-item"><a class="nav-link"><span>테스트케이스</span></a></router-link>
        </th>
        <th>
          <router-link :to="{name: 'TestDashboard'}" class="nav-item"><a class="nav-link"><span>대쉬보드</span></a></router-link>
        </th>
      </thead>
    </table>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent"
}
</script>

<style scoped>

</style>