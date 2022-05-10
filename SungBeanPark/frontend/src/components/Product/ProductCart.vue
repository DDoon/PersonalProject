<template>
  <div class="cart">
    <div class="container">
      <div class="row">
        <div class="cart-content-wrap col-sm-4 col-md-7 col-lg-8">
          <div class="cart-content">
            <div class="cart-content-header">
              <div class="cart-content-header left">
                <label for="">
                  <input type="checkbox" v-model="select_all" @click="select" />
                </label>
                <span>모두 선택</span>
              </div>

              <span class="cart-content-header right">
                <button
                  class="cart-content-hedaer delete"
                  @click="onDeleteProduct(cart.cartNo)"
                  aria-label="선택 삭제"
                >
                  선택삭제
                </button>
              </span>
            </div>
          </div>
          <div
            class="cart-content-nocart"
            v-if="
              !cartList || (Array.isArray(cartList) && cartList.length === 0)
            "
          >
            <i class="ic-cart"> </i>
          </div>
          <div
            class="cart-content-nocart-memo"
            v-if="
              !cartList || (Array.isArray(cartList) && cartList.length === 0)
            "
          >
            <strong>"장바구니가 비어 있습니다"</strong>
          </div>
          <ul class="cart-content-list">
            <li
              v-for="product in cartList"
              :key="product.productId"
              class="cart-content-item"
            >
              <article class="cart-content-product">
                <ul class="cart-content-option">
                  <li class="cart-content-option-list">
                    <article class="cart-content-article">
                      <div>
                        <div class="product-small-card select">
                          <input
                            type="checkbox"
                            :value="product.productId"
                            v-model="selected"
                          />
                        </div>
                        <div class="product-small-card">
                          <a class="product-small-item" href="/">
                            <div class="product-small-img">
                              <picture class="product-small-item picture">
                                <router-link
                                  :to="{
                                    name: 'ProductDetailPage',
                                    params: { productId: product.productId },
                                  }"
                                >
                                  <img
                                    v-if="product.fileName != null"
                                    :src="
                                      require(`@/assets/image/${product.fileName}`)
                                    "
                                    alt=""
                                  />
                                </router-link>
                              </picture>
                            </div>
                            <div class="product-small-item content">
                              {{ product.productName }}
                              <h3 class="product-small-item title"></h3>
                              <p>무료배송 &nbsp;|&nbsp; 화물택배</p>
                            </div>
                          </a>
                          <button
                            class="product-small-delete"
                            @click="onDeleteProduct(cartList[0].cart[0].cartNo)"
                            aria-label="상품삭제"
                          >
                            <i class="ic-close" aria-hidden="true"></i>
                          </button>
                        </div>

                        <div class="order-checkouts">
                          <ul class="checkout-list">
                            <li class="checkout-item">
                              <div class="checkout-card">
                                <header class="checkout-header">
                                  <h4 class="checkout-title">
                                    {{ product.productName }}
                                  </h4>
                                  <button
                                    class="delete-button"
                                    type="button"
                                    aria-label="해당 상품을 삭제하기"
                                  >
                                    <i class="ic-close" aria-hidden="true"></i>
                                  </button>
                                </header>

                                <div class="checkout-footer">
                                  <div class="checkout-select">
                                    <select id="checkout-item-1">
                                      <option
                                        v-for="quantity in 10"
                                        :key="quantity"
                                      >
                                        {{ quantity }}
                                      </option>
                                    </select>
                                    <span>
                                      <i
                                        class="ic-caret"
                                        aria-hidden="true"
                                      ></i>
                                    </span>
                                  </div>

                                  <output
                                    class="checkout-output"
                                    for="checkout-item-1"
                                  >
                                    <div class="price">
                                      <strong class="price">{{
                                        product.productPrice | pricePoint
                                      }}</strong>
                                    </div>
                                  </output>
                                </div>
                              </div>
                            </li>
                          </ul>
                        </div>

                        <div class="cart-content-footer">
                          <span class="cart-content-footer btn-group">
                            <button
                              class="cart-content-footer option"
                              type="button"
                            >
                              옵션변경
                            </button>
                            <button
                              class="cart-content-footer pay"
                              type="button"
                            >
                              바로구매
                            </button>
                          </span>
                          <strong class="price">{{
                            product.productPrice | pricePoint
                          }}</strong>
                        </div>
                      </div>
                    </article>
                  </li>
                </ul>
                <footer class="cart-sidebar-delivery">
                  <p>배송비 무료</p>
                </footer>
              </article>
            </li>
          </ul>
        </div>

        <div class="cart-sidebar-wrap col-sm-4 col-md-5 col-lg-4">
          <div class="cart-sidebar-sticky">
            <dl class="cart-sidebar">
              <div class="cart-sidebar-price">
                <dt>총 상품 금액</dt>
                <dd>
                  <span class="number">{{ sumProductPrice | pricePoint }}</span
                  >원
                </dd>
              </div>
              <div class="cart-sidebar-price">
                <dt>총 배송비</dt>
                <dd>+<span class="number">0</span>원</dd>
              </div>
              <div class="cart-sidebar-price">
                <dt>총 할인금액</dt>
                <dd>
                  -<span class="number">{{
                    sumProductDiscountPrice | pricePoint
                  }}</span
                  >원
                </dd>
              </div>
              <div class="cart-sidebar-price total">
                <dt>결제금액</dt>
                <dd>
                  <span class="number">{{ totalPrice | pricePoint }}</span
                  >원
                </dd>
              </div>
            </dl>
            <div class="cart-sidebar-btn">
              <button class="btn-black btn-46">상품 구매하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios"
import { mapState } from "vuex"
export default {
  name: "ProductCart",
  props: {
    cartList: {
      type: Array,
    },
  },
  data() {
    return {
      select_all: false,
      selected: [],
      quantity: 1,
    }
  },

  computed: {
    ...mapState["member"],

    sumProductPrice() {
      let sumProductPrice = 0
      for (let i = 0; i < this.cartList.length; i++) {
        sumProductPrice += this.cartList[i].productPrice
      }

      return sumProductPrice
    },
    sumProductDiscountPrice() {
      let sumProductDiscountPrice = 0
      for (let i = 0; i < this.cartList.length; i++) {
        sumProductDiscountPrice +=
          this.cartList[i].productPrice * (this.cartList[i].discount * 0.01)
      }

      return sumProductDiscountPrice
    },
    totalPrice() {
      let totalPrice = 0
      for (let i = 0; i < this.cartList.length; i++) {
        totalPrice +=
          this.cartList[i].productPrice -
          this.cartList[i].productPrice * (this.cartList[i].discount * 0.01)
      }

      return totalPrice
    },
  },

  filters: {
    pricePoint: function (value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    },
    ratingPoint: function (value) {
      return value.toString().replace(/\B(?=(\d{1})+(?!\d))/g, ".")
    },
  },
  methods: {
    onDeleteProduct(cartNo) {
      axios
        .delete(`http://localhost:8888/cart/cartRemove/${cartNo}`)
        .then(() => {
          alert("삭제되었습니다.")
          this.$router.go()
        })
        .catch(() => {
          alert("삭제요청실패")
        })
    },
    select() {
      this.selected = []
      if (!this.select_all) {
        for (let i in this.cartList) {
          this.selected.push(this.cartList[i].productId)
        }
      }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layout/cart";
@import "~@/assets/scss/components/checkout-card";
@import "~@/assets/scss/components/product-small";
</style>
