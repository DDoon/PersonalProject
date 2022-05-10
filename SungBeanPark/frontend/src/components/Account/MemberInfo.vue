<template>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <div class="myhome-edit" name="MemberInfo" @submit.prevent="onSubmit">
            <div class="myhome-edit-header">
              <div class="myhome-edit-header title">회원정보수정</div>
              <a class="myhome-edit-header link" href="">탈퇴하기</a>
            </div>
            <form class="myhome-edit-form">
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">이메일</div>
                <div class="myhome-edit-inputgroup">
                  <span class="myhome-edit-inputgroup-email">
                    <input
                      class="myhome-edit-item input"
                      type="text"
                      placeholder="이메일"
                      value
                    />
                  </span>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">별명</div>
                <div class="myhome-edit-inputgroup">
                  <span>
                    <input
                      v-model="name"
                      class="myhome-edit-item input"
                      type="text"
                      placeholder="별명"
                      value
                    />
                  </span>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">비밀번호</div>
                <div class="myhome-edit-inputgroup">
                  <span>
                    <input
                      v-model="pw"
                      class="myhome-edit-item input"
                      type="password"
                      placeholder="비밀번호"
                    />
                  </span>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">비밀번호 확인</div>
                <div class="myhome-edit-inputgroup">
                  <span>
                    <input
                      class="myhome-edit-item input"
                      type="password"
                      placeholder="비밀번호 확인"
                      value
                    />
                  </span>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">성별</div>
                <div class="myhome-edit-item radiogroup">
                  <ul class="myhome-edit-item radio">
                    <li>
                      <div class="form-radio">
                        <label class="form-radio-label" for="">
                          <input type="radio" />
                          <span>남성</span>
                        </label>
                      </div>
                    </li>
                    <li>
                      <div class="form-radio">
                        <label class="form-radio-label" for="">
                          <input type="radio" />
                          <span>여성</span>
                        </label>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">생년월일</div>
                <div class="myhome-edit-inputgroup">
                  <span>
                    <input
                      class="myhome-edit-item input"
                      type="text"
                      placeholder="YYYY-MM-DD"
                      value
                    />
                  </span>
                </div>
              </div>
              <div class="myhome-edit-item">
                <div class="myhome-edit-item title">주소</div>
                <div class="myhome-edit-inputgroup">
                  <span>
                    <input
                      class="myhome-edit-item input"
                      type="text"
                      placeholder="주소"
                      value
                    />
                  </span>
                </div>
              </div>
              <button class="btn-black btn-46">수정하기</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>
<script>
export default {
  name: "MemberInfo",
  props: {
    userInfo: {
      type: Object,
      required: true,
    },
  },
  created() {
    this.memberNo = this.userInfo.memberNo
    this.pw = this.userInfo.pw
    this.name = this.userInfo.name
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layout/myhome";
</style>
