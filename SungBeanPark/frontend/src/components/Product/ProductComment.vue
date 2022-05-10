<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-4 col-md-6 col-lg-7">
        <section
          enctype="multipart/form-data"
          @submit.prevent="onSubmit"
          class="product-section product-review"
          id="product-review"
          role="tabpanel"
        >
          <header class="product-section-header">
            <h2 class="title">리뷰 {{ productComments.length }}</h2>
          </header>

          <div class="review-scoreboard">
            <div class="score-summary">
              <strong class="average-score"></strong>
              <div class="star-rating">
                <star-rating
                  :inline="true"
                  :read-only="true"
                  :star-size="30"
                  :v-model="totalrate"
                  :incremane="0.1"
                  active-color="#fee500"
                  :star-points="[
                    23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36, 34,
                    46, 19, 31, 17,
                  ]"
                ></star-rating>
              </div>
            </div>

            <div class="score-detail">
              <dl class="score-stats-list">
                <div class="score-stats-item">
                  <dt>5점</dt>
                  <dd>
                    <div class="bar-graph" aria-hidden="true">
                      <div class="active-bar" style="width: 0%"></div>
                    </div>
                    <strong class="count"></strong>
                  </dd>
                </div>

                <div class="score-stats-item">
                  <dt>4점</dt>
                  <dd>
                    <div class="bar-graph" aria-hidden="true">
                      <div class="active-bar" style="width: 0%"></div>
                    </div>
                    <strong class="count"></strong>
                  </dd>
                </div>

                <div class="score-stats-item">
                  <dt>3점</dt>
                  <dd>
                    <div class="bar-graph" aria-hidden="true">
                      <div class="active-bar" style="width: 0%"></div>
                    </div>
                    <strong class="count"></strong>
                  </dd>
                </div>

                <div class="score-stats-item">
                  <dt>2점</dt>
                  <dd>
                    <div class="bar-graph" aria-hidden="true">
                      <div class="active-bar" style="width: 0%"></div>
                    </div>
                    <strong class="count"></strong>
                  </dd>
                </div>

                <div class="score-stats-item">
                  <dt>1점</dt>
                  <dd>
                    <div class="bar-graph" aria-hidden="true">
                      <div class="active-bar" style="width: 0%"></div>
                    </div>
                    <strong class="count"></strong>
                  </dd>
                </div>
              </dl>
            </div>
          </div>

          <form>
            <div
              v-for="commenList in paginatedData"
              :key="commenList.commentNo"
              class="product-section-content"
            >
              <ol class="review-list">
                <li
                  class="review-item"
                  style="border-bottom: 1px solid #e0e2e7"
                >
                  <article class="review-card">
                    <header class="review-card-header">
                      <h3 class="visually-hidden">
                        {{ commenList.writer }}님이 작성한 리뷰
                      </h3>

                      <div class="info">
                        <a class="username" href="/">
                          <strong>{{ commenList.writer }}</strong>
                        </a>

                        <div class="detail">
                          <div
                            class="star-rating-13"
                            aria-label="5.0점 / 5.0점"
                          >
                            <star-rating
                              :inline="true"
                              :read-only="true"
                              :star-size="10"
                              :show-rating="false"
                              v-model="commenList.rating"
                              :incremane="0.1"
                              active-color="#fee500"
                              :star-points="[
                                23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38,
                                50, 36, 34, 46, 19, 31, 17,
                              ]"
                            ></star-rating>
                          </div>
                          <div class="misc">
                            {{ commenList.regDate }}
                          </div>
                        </div>
                      </div>
                    </header>
                    <div class="review-card-body">
                      <div
                        class="review-image"
                        v-if="commenList.fileName != null"
                      >
                        <img
                          :src="
                            require(`@/assets/image/${commenList.fileName}`)
                          "
                          alt="리뷰사진"
                        />
                      </div>
                      <p>
                        {{ commenList.comment }}
                      </p>
                    </div>

                    <footer class="review-card-footer">
                      <!-- <button
                        @click="onLike(commenList.commentNo)"
                        class="btn-outlined btn-42"
                        type="button"
                        aria-label="좋아요 버튼"
                      >
                        도움이 돼요
                      </button> -->
                      <button
                        @click="onLike(commenList.commentNo)"
                        class="btn-black btn-42"
                        type="button"
                        aria-label="좋아요 버튼"
                      >
                        <i class="ic-check" aria-hidden="true"></i>
                        도움됨
                      </button>
                      <p>
                        <strong
                          ><span> {{ commenList.likes.length }}명</span></strong
                        >에게 도움이 되었습니다.
                      </p>
                    </footer>
                    <button
                      v-if="auth == '관리자' || commenList.writer == userName"
                      @click="onDelete(commenList.commentNo)"
                      class="delete-button"
                      type="button"
                      aria-label="해당 리뷰 삭제하기"
                    >
                      <i class="ic-close" aria-hidden="true"></i>
                    </button>
                  </article>
                </li>
              </ol>
            </div>

            <div class="pagination">
              <a href="#location">
                <button
                  class="page-control page-prev"
                  :class="{ active: pageNum + 1 === firstPage }"
                  @click="prevPage"
                  type="button"
                  aria-label="이전페이지로 이동"
                >
                  <i class="ic-chevron" aria-hidden="true"></i>
                </button>
              </a>
              <ol class="page-list">
                <li
                  class="page-item"
                  v-for="n in pageCount"
                  :key="n"
                  @click="setPage(n - 1)"
                  :class="{ isactive: n == pageNum + 1 }"
                >
                  <a href="#location">
                    {{ n }}
                  </a>
                </li>
              </ol>

              <a href="#location">
                <button
                  :class="{ active: pageNum + 1 === lastPage }"
                  @click="nextPage"
                  class="page-control page-next"
                  type="button"
                  aria-label="다음페이지로 이동"
                >
                  <i class="ic-chevron" aria-hidden="true"></i>
                </button>
              </a>
            </div>
          </form>

          <div class="review-modal">
            <div class="review-modal-title">별점 평가</div>
            <div class="review-modal-star">
              <div class="review-modal-star-label">만족도</div>
              <star-rating
                v-model="rating"
                :show-rating="false"
                :rounded-corners="true"
                inactive-color="#e0e2e7"
                active-color="#fee500"
                :star-points="[
                  23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36, 34,
                  46, 19, 31, 17,
                ]"
              ></star-rating>
            </div>
          </div>

          <div class="review-file">
            <div class="review-card-content header">사진 첨부(선택)</div>

            <div class="review-card-btn">
              <div class="select-picture" v-if="image">
                <img
                  class="select-picture-contents"
                  :src="image"
                  alt="첨부한 사진"
                />
                <button
                  @click="imageDelete"
                  class="delete-button"
                  type="button"
                  aria-label="첨부 파일 삭제하기"
                >
                  <i class="ic-close" aria-hidden="true"></i>
                </button>
              </div>
              <input
                class="visually-hidden"
                id="files"
                type="file"
                ref="files"
                multiple
                v-if="uploadReady"
                v-on:change="commentFileUpload()"
              />
              <button
                class="btn-black btn-46"
                type="button"
                @click="onUpload"
                aria-label="사진 첨부하기"
              >
                사진 첨부하기
              </button>
            </div>
          </div>

          <form class="review-form">
            <div class="review-card-content">
              <div class="review-card-content header">리뷰 작성</div>
              <textarea
                v-model="comment"
                style="border-radius: 4px"
                class="form-control"
                minlength="20"
                maxlength="1000"
                cols="120"
                rows="32"
                placeholder="자세하고 솔직한 리뷰는 다른 고객에게 큰 도움이 됩니다.(최소 20자 이상)"
              ></textarea>
            </div>
            <div class="review-card-btn">
              <button class="btn-black btn-46" type="submit">완료</button>
            </div>
          </form>
        </section>
      </div>
    </div>
  </div>
</template>
<script>
import StarRating from "vue-star-rating"
import axios from "axios"
export default {
  name: "ProductComment",
  components: {
    StarRating,
  },
  props: {
    productComments: {
      type: Array,
    },
    listArray: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      userInfo: this.$store.state.userInfo,
      uploadReady: true,
      commentNo: "",
      comment: "",
      fileName: "",
      regDate: "",
      image: "",
      writer: "",
      files: [],
      id: "",
      totalrate: 0,
      rating: 0,

      pageNum: 0,
      pageSize: 3, // listSize라고 생각하자
      firstPage: 1,
      lastPage: 0,

      auth: "",
    }
  },

  created() {
    if (this.$store.state.userInfo != null) {
      this.userName = this.$store.state.userInfo.userName
      this.auth = this.userInfo.auth
    }
    this.writer = this.$store.state.userInfo.id
    this.commentNo = this.productComments.commentNo
  },
  methods: {
    imageDelete() {
      this.image = null

      this.clearImage()
    },
    clearImage() {
      this.uploadReady = false
      this.$nextTick(() => {
        this.uploadReady = true
      })
    },
    onUpload() {
      this.$refs.files.click()
    },
    onDelete(commentNo) {
      console.log(commentNo)
      axios
        .delete(`http://localhost:8888/product/comment/delete/${commentNo}`, {
          commentNo,
        })
        .then(() => {
          alert("삭제 성공!")
          history.go(0)
        })
        .catch(() => {
          alert("삭제 실패")
        })
    },
    onSubmit() {
      const { comment, writer, rating } = this
      const file = this.$refs.files.files[0]
      this.$emit("submit", { comment, writer, file, rating })
      console.log(comment, writer, file, rating)
    },
    onLike(commentNo) {
      if (this.userInfo != null) {
        axios
          .post(
            `http://localhost:8888/like/${commentNo}/${this.userInfo.memberNo}`,
            {
              commentNo,
              memberNo: this.userInfo.memberNo,
            }
          )
          .then((res) => {
            if (res.data) {
              alert("좋아요 성공")
              this.$router.go()
            } else {
              alert("이미 좋아요를 누르셨습니다.")
            }
          })
          .catch(() => {
            alert("오류 발생")
          })
      } else {
        alert("로그인 후 이용해주세요.")
      }
    },
    commentFileUpload() {
      var image = this.$refs["files"].files[0]
      this.image = URL.createObjectURL(image)
      this.files = this.$refs.files.files[0]
    },

    nextPage() {
      if (this.pageNum === this.pageCount - 1) {
        this.pageNum = this.pageCount - 1
      } else {
        this.pageNum += 1
      }
    },

    prevPage() {
      if (this.pageNum === 0) {
        this.pageNum = 0
      } else {
        this.pageNum -= 1
      }
    },

    setPage(v) {
      this.pageNum = v
    },
  },

  computed: {
    pageCount() {
      let listLeng = this.listArray.length
      let listSize = this.pageSize
      let page = Math.floor(listLeng / listSize)
      if (listLeng % listSize > 0) page += 1

      return page
    },

    paginatedData() {
      const start = this.pageNum * this.pageSize

      const end = start + this.pageSize

      return this.listArray.slice(start, end)
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/review-card";
@import "~@/assets/scss/modules/select-picture";
</style>
