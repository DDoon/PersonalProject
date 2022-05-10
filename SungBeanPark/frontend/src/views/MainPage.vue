<template>
  <body>
    <header-layout></header-layout>
    <section class="home-section sm-only">
      <div class="home-section-content">
        <p class="home-section-text">
          <router-link :to="{ name: 'SignPage' }">회원 가입</router-link>하고
          10% 할인 쿠폰, 무료 배송 등의 혜택을 받으세요.
        </p>
      </div>
    </section>
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <div class="slide-section sm-only">
            <a href="">
              <img src="@/assets/image/nike-m.gif" alt="nike" />
            </a>
          </div>
          <div class="slide-section sm-hidden">
            <a href="">
              <img
                src="https://static-breeze.nike.co.kr/kr/ko_kr//cmsstatic/structured-content/65250/220324_hp_p1_bg.gif"
                alt="nike"
              />
            </a>
          </div>

          <figcaption class="slide-figcaption">
            <div class="slide-figcaption-container">
              <div class="slide-figcaption-text">
                <h2>에어맥스 데이</h2>
                <p>무한한 창의성, 혁신, 상상력을 선보인</p>
                <br />
                <p>에어맥스의 35주년을 함께 기념해보세요.</p>
              </div>
              <div class="slide-figcaption-btn">
                <a class="btn-blue btn-46" href="">구매하기</a>
              </div>
            </div>
          </figcaption>

          <figure class="figure-content">
            <a href="/">
              <div class="sm-only">
                <video
                  src="https://nikevideo.nike.com/media/v1/pmp4/static/clear/72451143001/ed85e2a8-1a30-4fbe-9892-5edbba7765d7/ce613cc3-ee51-429b-8f77-ed710f9e92b8/main.mp4"
                  autoplay="autoplay"
                  muted="muted"
                >
                  <source
                    src="https://nikevideo.nike.com/media/v1/pmp4/static/clear/72451143001/ed85e2a8-1a30-4fbe-9892-5edbba7765d7/ce613cc3-ee51-429b-8f77-ed710f9e92b8/main.mp4"
                    type="video/mp4"
                  />
                </video>
              </div>
              <div class="sm-hidden">
                <video
                  src="https://nikevideo.nike.com/media/v1/pmp4/static/clear/72451143001/ed85e2a8-1a30-4fbe-9892-5edbba7765d7/ce613cc3-ee51-429b-8f77-ed710f9e92b8/main.mp4"
                  autoplay="autoplay"
                  muted="muted"
                >
                  <source
                    src="https://nikevideo.nike.com/media/v1/pmp4/static/clear/72451143001/ed85e2a8-1a30-4fbe-9892-5edbba7765d7/ce613cc3-ee51-429b-8f77-ed710f9e92b8/main.mp4"
                    type="video/mp4"
                  />
                </video>
              </div>
            </a>
            <figcaption class="slide-figcaption">
              <div class="slide-figcaption-container">
                <div class="slide-figcaption-text">
                  <h2>
                    ROBLOX에서 만나는 <br />
                    NIKELAND
                  </h2>
                  <p>
                    정해진 규칙이 없는 스포츠의 세계, NiKELAND로 초대합니다.
                  </p>
                  <br />
                  <p>에어토피아에서 펼쳐지는 마법 같은 순간을 만나보세요.</p>
                </div>
                <div class="slide-figcaption-btn">
                  <a class="btn-blue btn-46" href="">구매하기</a>
                </div>
              </div>
            </figcaption>
          </figure>

          <swiper-section></swiper-section>
        </div>
      </div>
    </div>

    <!-- Footer ic추가해야함 -->
    <footer-layout></footer-layout>
  </body>
</template>
<script>
import HeaderLayout from "@/components/Header/HeaderLayout.vue"
import FooterLayout from "@/components/Footer/FooterLayout.vue"
import SwiperSection from "@/components/Section/SwiperSection.vue"
export default {
  name: "MainPage",

  components: {
    HeaderLayout,
    FooterLayout,
    SwiperSection,
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/layout/sidebar";
@import "~@/assets/scss/layout/figcaption";
@import "~@/assets/scss/layout/figure";
@import "~@/assets/scss/layout/slide";
@import "~@/assets/scss/components/gnb-nav";
@import "~@/assets/scss/components/gnb-icon-buttons";
@import "~@/assets/scss/components/home-section";
</style>
