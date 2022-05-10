<template>
  <div class="product-register">
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <form enctype="multipart/form-data" @submit.prevent="onSubmit">
            <header class="product-register-header">
              <h2 class="title">상품등록</h2>
            </header>

            <table class="product-register-table">
              <div class="product-register-name">
                <label for="">상품명</label>
                <input v-model="productName" type="text" />
              </div>
              <div class="product-register-price">
                <label for="">상품가</label>
                <input v-model="productPrice" type="number" />
              </div>
              <div class="product-register-gender">
                <label for="">남여전용</label>
                <input v-model="gender" type="text" />
              </div>

              <div class="product-register-discount">
                <label for="">할인율</label>
                <input v-model="discount" type="number" />
              </div>
              <div class="product-register-rating">
                <label for="">별점수</label>
                <input v-model="rating" type="number" />
              </div>

              <button class="btn-black btn-46" type="submit">등록하기</button>
            </table>
            <div>
              <input
                type="file"
                id="files"
                ref="files"
                multiple
                v-on:change="handlerFileUpload()"
              />
              <div>
                <img class="preview" :src="image" alt="" />
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ProductRegister",

  data() {
    return {
      productName: "",
      productPrice: 0,
      gender: "",
      productDiscountPrice: 0,
      discount: 0,
      rating: 0,
      files: "",
      image: "",
    }
  },
  methods: {
    onSubmit() {
      const {
        productName,
        productPrice,
        gender,
        productDiscountPrice,
        discount,
        rating,
      } = this
      const file = this.$refs.files.files[0]
      if (file == null) {
        alert("대표 이미지를 등록해주세요.")
        return
      }
      this.$emit("submit", {
        productName,
        productPrice,
        gender,
        productDiscountPrice,
        discount,
        rating,
        file,
      })
    },
    handlerFileUpload() {
      var image = this.$refs["files"].files[0]
      const url = URL.createObjectURL(image)
      this.image = url
      this.files = this.$refs.files.files[0]
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/product-register";
</style>
