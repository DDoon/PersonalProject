<template>
  <div>
    <header-layout></header-layout>
    <member-info :userInfo="userInfo"></member-info>
    <footer-layout></footer-layout>
  </div>
</template>
<script>
import MemberInfo from "@/components/Account/MemberInfo.vue"
import { mapActions, mapState } from "vuex"
import HeaderLayout from "@/components/Header/HeaderLayout.vue"
import FooterLayout from "@/components/Footer/FooterLayout.vue"

export default {
  name: "MemberInfoPage",
  components: {
    MemberInfo,
    FooterLayout,
    HeaderLayout,
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    this.fetchMember(this.$store.state.userInfo.memberNo).catch(() => {
      alert("요청 실패")
      this.$router.push()
    })
  },
  methods: {
    ...mapActions(["fetchMember"]),
  },
}
</script>
<style></style>
