function Page(_ref) { var pageSize = _ref.pageSize, pageTotal = _ref.pageTotal, curPage = _ref.curPage, id = _ref.id, getPage = _ref.getPage, showPageTotalFlag = _ref.showPageTotalFlag, showSkipInputFlag = _ref.showSkipInputFlag, pageAmount = _ref.pageAmount, dataTotal = _ref.dataTotal; if (!pageSize) { pageSize = 0 }; if (!pageSize) { pageSize = 0 }; if (!pageTotal) { pageTotal = 0 }; if (!pageAmount) { pageAmount = 0 }; if (!dataTotal) { dataTotal = 0 }; this.pageSize = pageSize || 5; this.pageTotal = pageTotal; this.pageAmount = pageAmount; this.dataTotal = dataTotal; this.curPage = curPage || 1; this.ul = document.createElement('ul'); this.id = id; this.getPage = getPage; this.showPageTotalFlag = showPageTotalFlag || false; this.showSkipInputFlag = showSkipInputFlag || false; if (dataTotal > 0 && pageTotal > 0) { this.init(); } else { console.error("鎬婚〉鏁版垨鑰呮€绘暟鎹弬鏁颁笉瀵�") } }; Page.prototype = {
    init: function init() {
        var pagination = document.getElementById(this.id); pagination.innerHTML = ''; this.ul.innerHTML = ''; pagination.appendChild(this.ul); var that = this; that.firstPage(); that.lastPage(); that.getPages().forEach(function (item) {
            var li = document.createElement('li'); if (item == that.curPage) { li.className = 'active'; } else { li.onclick = function () { that.curPage = parseInt(this.innerHTML); that.init(); that.getPage(that.curPage); }; }
            li.innerHTML = item; that.ul.appendChild(li);
        }); that.nextPage(); that.finalPage(); if (that.showSkipInputFlag) { that.showSkipInput(); }
        if (that.showPageTotalFlag) { that.showPageTotal(); }
    }, firstPage: function firstPage() { var that = this; var li = document.createElement('li'); li.innerHTML = '首页'; this.ul.appendChild(li); li.onclick = function () { var val = parseInt(1); that.curPage = val; that.getPage(that.curPage); that.init(); }; }, lastPage: function lastPage() {
        var that = this; var li = document.createElement('li'); li.innerHTML = '<'; if (parseInt(that.curPage) > 1) { li.onclick = function () { that.curPage = parseInt(that.curPage) - 1; that.init(); that.getPage(that.curPage); }; } else { li.className = 'disabled'; }
        this.ul.appendChild(li);
    }, getPages: function getPages() {
        var pag = []; if (this.curPage <= this.pageTotal) {
            if (this.curPage < this.pageSize) { var i = Math.min(this.pageSize, this.pageTotal); while (i) { pag.unshift(i--); } } else {
                var middle = this.curPage - Math.floor(this.pageSize / 2), i = this.pageSize; if (middle > this.pageTotal - this.pageSize) { middle = this.pageTotal - this.pageSize + 1; }
                while (i--) { pag.push(middle++); }
            }
        } else { console.error('褰撳墠椤垫暟涓嶈兘澶т簬鎬婚〉鏁�'); }
        if (!this.pageSize) { console.error('鏄剧ず椤垫暟涓嶈兘涓虹┖鎴栬€�0'); }
        return pag;
    }, nextPage: function nextPage() {
        var that = this; var li = document.createElement('li'); li.innerHTML = '>'; if (parseInt(that.curPage) < parseInt(that.pageTotal)) { li.onclick = function () { that.curPage = parseInt(that.curPage) + 1; that.init(); that.getPage(that.curPage); }; } else { li.className = 'disabled'; }
        this.ul.appendChild(li);
    }, finalPage: function finalPage() { var that = this; var li = document.createElement('li'); li.innerHTML = '尾页'; this.ul.appendChild(li); li.onclick = function () { var yyfinalPage = that.pageTotal; var val = parseInt(yyfinalPage); that.curPage = val; that.getPage(that.curPage); that.init(); }; }, showSkipInput: function showSkipInput() {
        var that = this; var li = document.createElement('li'); li.className = 'totalPage'; var span1 = document.createElement('span'); span1.innerHTML = '跳转到'; li.appendChild(span1); var input = document.createElement('input'); input.setAttribute("type", "number"); input.onkeydown = function (e) {
            var oEvent = e || event; if (oEvent.keyCode == '13') {
                var val = parseInt(oEvent.target.value); if (typeof val === 'number' && val <= that.pageTotal && val > 0) { that.curPage = val; that.getPage(that.curPage); } else { alert("璇疯緭鍏ユ纭殑椤垫暟 !") }
                that.init();
            }
        }; li.appendChild(input); var span2 = document.createElement('span'); span2.innerHTML = '页'; li.appendChild(span2); this.ul.appendChild(li);
    }, showPageTotal: function showPageTotal() { var that = this; var li = document.createElement('li'); li.innerHTML = '共' + that.pageTotal + '&nbsp页'; li.className = 'totalPage'; this.ul.appendChild(li); var li2 = document.createElement('li'); li2.innerHTML = '每页' + that.pageAmount + '条'; li2.className = 'totalPage'; this.ul.appendChild(li2); var li3 = document.createElement('li'); li3.innerHTML = '合计' + that.dataTotal + '条数据'; li3.className = 'totalPage'; this.ul.appendChild(li3); }
};