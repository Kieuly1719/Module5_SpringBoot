(function () {
    const pageSize = 20;
    let currentPage = 0;
    let currentKeyword = "";
    let isLoading = false;

    const blogList = document.getElementById("blog-list");
    const searchForm = document.getElementById("search-form");
    const keywordInput = document.getElementById("keyword");
    const resultSummary = document.getElementById("result-summary");
    const loadMoreButton = document.getElementById("load-more");
    const clearSearchButton = document.getElementById("clear-search");
    const emptyState = document.getElementById("empty-state");
    const modal = document.getElementById("detail-modal");

    function requestBlogs(keyword, page) {
        const url = `/api/blogs?keyword=${encodeURIComponent(keyword)}&page=${page}&size=${pageSize}`;

        if (window.jQuery) {
            return $.ajax({
                url: url,
                method: "GET",
                dataType: "json"
            });
        }

        return fetch(url).then(function (response) {
            if (!response.ok) {
                throw new Error("Request failed");
            }
            return response.json();
        });
    }

    function escapeHtml(value) {
        return String(value ?? "")
            .replaceAll("&", "&amp;")
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll('"', "&quot;")
            .replaceAll("'", "&#039;");
    }

    function formatDate(value) {
        if (!value) {
            return "";
        }
        return new Intl.DateTimeFormat("vi-VN", {
            dateStyle: "medium",
            timeStyle: "short"
        }).format(new Date(value));
    }

    function createBlogCard(blog) {
        const article = document.createElement("article");
        article.className = "blog-card";
        article.innerHTML = `
            <div>
                <div class="card-meta">
                    <span class="category-pill">${escapeHtml(blog.category.name)}</span>
                    <span>${escapeHtml(formatDate(blog.createdAt))}</span>
                </div>
                <h2>${escapeHtml(blog.title)}</h2>
                <p>${escapeHtml(blog.summary)}</p>
            </div>
            <button class="detail-button" type="button" data-blog-id="${blog.id}">Xem chi tiet</button>
        `;

        article.querySelector(".detail-button").addEventListener("click", function () {
            openDetail(blog);
        });

        return article;
    }

    function renderPage(data, shouldAppend) {
        if (!shouldAppend) {
            blogList.innerHTML = "";
        }

        data.content.forEach(function (blog) {
            blogList.appendChild(createBlogCard(blog));
        });

        const keywordText = currentKeyword ? ` cho tu khoa "${currentKeyword}"` : "";
        resultSummary.textContent = `Tim thay ${data.totalElements} bai viet${keywordText}. Dang hien thi ${blogList.children.length} bai.`;
        emptyState.hidden = data.totalElements !== 0;
        clearSearchButton.hidden = currentKeyword.length === 0;
        loadMoreButton.hidden = data.last || data.totalElements === 0;
        currentPage = data.page;
    }

    function loadBlogs(page, shouldAppend) {
        if (isLoading) {
            return;
        }

        isLoading = true;
        loadMoreButton.disabled = true;
        loadMoreButton.textContent = "Dang tai...";
        resultSummary.textContent = page === 0 ? "Dang tai danh sach bai viet..." : "Dang tai them bai viet...";

        const request = requestBlogs(currentKeyword, page)
            .then(function (data) {
                renderPage(data, shouldAppend);
            })
            .catch(function () {
                resultSummary.textContent = "Khong tai duoc du lieu. Vui long thu lai.";
            });

        if (typeof request.always === "function") {
            request.always(finishLoading);
        } else {
            request.finally(finishLoading);
        }
    }

    function finishLoading() {
        isLoading = false;
        loadMoreButton.disabled = false;
        loadMoreButton.textContent = "Tai them";
    }

    function openDetail(blog) {
        document.getElementById("detail-category").textContent = blog.category.name;
        document.getElementById("detail-title").textContent = blog.title;
        document.getElementById("detail-date").textContent = formatDate(blog.createdAt);
        document.getElementById("detail-summary").textContent = blog.summary;
        document.getElementById("detail-content").textContent = blog.content;
        modal.classList.add("is-open");
        modal.setAttribute("aria-hidden", "false");
    }

    function closeDetail() {
        modal.classList.remove("is-open");
        modal.setAttribute("aria-hidden", "true");
    }

    searchForm.addEventListener("submit", function (event) {
        event.preventDefault();
        currentKeyword = keywordInput.value.trim();
        loadBlogs(0, false);
    });

    loadMoreButton.addEventListener("click", function () {
        loadBlogs(currentPage + 1, true);
    });

    clearSearchButton.addEventListener("click", function () {
        keywordInput.value = "";
        currentKeyword = "";
        loadBlogs(0, false);
    });

    document.getElementById("close-detail").addEventListener("click", closeDetail);
    modal.addEventListener("click", function (event) {
        if (event.target === modal) {
            closeDetail();
        }
    });

    loadBlogs(0, false);
})();
