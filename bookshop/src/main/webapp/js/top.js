/*document.addEventListener("DOMContentLoaded", () => {
  const slidesbox = document.querySelector(".slidesbox");
  const books = document.querySelectorAll(".slidesbox .book-area");
  const prev = document.querySelector(".prev");
  const next = document.querySelector(".next");

  let index = 0;
  const visibleCount = 5;

  const style = getComputedStyle(books[0]);
  const margin =
    parseFloat(style.marginLeft) + parseFloat(style.marginRight);
  const slideWidth = books[0].offsetWidth + margin;

  next.addEventListener("click", () => {
    if (index < books.length - visibleCount) {
      index++;
      slidesbox.style.transform =
        `translateX(-${slideWidth * index}px)`;
    }
  });

  prev.addEventListener("click", () => {
    if (index > 0) {
      index--;
      slidesbox.style.transform =
        `translateX(-${slideWidth * index}px)`;
    }
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const slidesbox = document.querySelector(".slidesbox");
  const books = document.querySelectorAll(".slidesbox .book-area");
  const prev = document.querySelector(".prev");
  const next = document.querySelector(".next");

  let index = 0;
  const visibleCount = 5;
  const maxIndex = books.length - visibleCount;

  const style = getComputedStyle(books[0]);
  const margin =
    parseFloat(style.marginLeft) + parseFloat(style.marginRight);
  const slideWidth = books[0].offsetWidth + margin;

  function updateSlide() {
    slidesbox.style.transform =
      `translateX(-${slideWidth * index}px)`;
  }

  next.addEventListener("click", () => {
    if (index < maxIndex) {
      index++;
    } else {
      index = 0; // ← 最後なら最初へ
    }
    updateSlide();
  });

  prev.addEventListener("click", () => {
    if (index > 0) {
      index--;
    } else {
      index = maxIndex; // ← 最初なら最後へ
    }
    updateSlide();
  });
});
document.addEventListener("DOMContentLoaded", () => {
  const slidesbox = document.querySelector(".slidesbox");
  const prev = document.querySelector(".prev");
  const next = document.querySelector(".next");

  const visibleCount = 5;
  let books = document.querySelectorAll(".slidesbox .book-area");

  const style = getComputedStyle(books[0]);
  const margin =
    parseFloat(style.marginLeft) + parseFloat(style.marginRight);
  const slideWidth = books[0].offsetWidth + margin;

  
  for (let i = books.length - visibleCount; i < books.length; i++) {
    slidesbox.prepend(books[i].cloneNode(true));
  }
  for (let i = 0; i < visibleCount; i++) {
    slidesbox.append(books[i].cloneNode(true));
  }
  books = document.querySelectorAll(".slidesbox .book-area");

  let index = visibleCount;

  slidesbox.style.transform =
    `translateX(-${slideWidth * index}px)`;

  function moveSlide() {
    slidesbox.style.transition = "transform 0.4s ease";
    slidesbox.style.transform =
      `translateX(-${slideWidth * index}px)`;
  }

  function nextSlide() {
    index++;
    moveSlide();

    if (index === books.length - visibleCount) {
      setTimeout(() => {
        slidesbox.style.transition = "none";
        index = visibleCount;
        slidesbox.style.transform =
          `translateX(-${slideWidth * index}px)`;
      }, 400);
    }
  }

  function prevSlide() {
    index--;
    moveSlide();

    if (index === 0) {
      setTimeout(() => {
        slidesbox.style.transition = "none";
        index = books.length - visibleCount * 2;
        slidesbox.style.transform =
          `translateX(-${slideWidth * index}px)`;
      }, 400);
    }
  }

  next.addEventListener("click", nextSlide);
  prev.addEventListener("click", prevSlide);

  let timer = null;
  const interval = 3000; // 3秒

  function startAuto() {
    timer = setInterval(nextSlide, interval);
  }

  function stopAuto() {
    clearInterval(timer);
    timer = null;
  }
  
  

  startAuto();

  // マウス操作で停止／再開
  slidesbox.addEventListener("mouseenter", stopAuto);
  slidesbox.addEventListener("mouseleave", startAuto);

  // ボタン操作時もリセット
  prev.addEventListener("click", () => {
    stopAuto();
    startAuto();
  });

  next.addEventListener("click", () => {
    stopAuto();
    startAuto();
  });
});
*/
document.addEventListener("DOMContentLoaded", () => {

  document.querySelectorAll(".slideshow").forEach(slideshow => {

    const slidesbox = slideshow.querySelector(".slidesbox");
    const prev = slideshow.querySelector(".prev");
    const next = slideshow.querySelector(".next");

    const visibleCount = 5;
    let books = slidesbox.querySelectorAll(".book-area");

    if (books.length <= visibleCount) return;

    /* ===== サイズ計算 ===== */
    const style = getComputedStyle(books[0]);
    const margin =
      parseFloat(style.marginLeft) + parseFloat(style.marginRight);
    const slideWidth = books[0].offsetWidth + margin;

    /* ===== クローン ===== */
    for (let i = books.length - visibleCount; i < books.length; i++) {
      slidesbox.prepend(books[i].cloneNode(true));
    }
    for (let i = 0; i < visibleCount; i++) {
      slidesbox.append(books[i].cloneNode(true));
    }

    books = slidesbox.querySelectorAll(".book-area");

    /* ===== 初期位置 ===== */
    let index = visibleCount;
    slidesbox.style.transition = "none";
    slidesbox.style.transform =
      `translateX(-${slideWidth * index}px)`;
    slidesbox.offsetHeight;
    slidesbox.style.transition = "transform 0.4s ease";

    /* ===== スライド処理 ===== */
    function nextSlide() {
      index++;
      slidesbox.style.transform =
        `translateX(-${slideWidth * index}px)`;

      if (index === books.length - visibleCount) {
        setTimeout(() => {
          slidesbox.style.transition = "none";
          index = visibleCount;
          slidesbox.style.transform =
            `translateX(-${slideWidth * index}px)`;
          slidesbox.offsetHeight;
          slidesbox.style.transition = "transform 0.4s ease";
        }, 400);
      }
    }

    function prevSlide() {
      index--;
      slidesbox.style.transform =
        `translateX(-${slideWidth * index}px)`;

      if (index === 0) {
        setTimeout(() => {
          slidesbox.style.transition = "none";
          index = books.length - visibleCount * 2;
          slidesbox.style.transform =
            `translateX(-${slideWidth * index}px)`;
          slidesbox.offsetHeight;
          slidesbox.style.transition = "transform 0.4s ease";
        }, 400);
      }
    }

    next.addEventListener("click", nextSlide);
    prev.addEventListener("click", prevSlide);

    /* ===== 自動スクロール =====
    let timer = null;
    const interval = 3000;

    function startAuto() {
      if (timer) return;
      timer = setInterval(nextSlide, interval);
    }

    function stopAuto() {
      clearInterval(timer);
      timer = null;
    }

    setTimeout(startAuto, 1500);

    slideshow.addEventListener("mouseenter", stopAuto);
    slideshow.addEventListener("mouseleave", startAuto); */

  });
});
