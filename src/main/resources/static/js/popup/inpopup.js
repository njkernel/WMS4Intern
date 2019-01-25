$(function () {

  var Plugin = function (elem, options) {
    this.$elem = elem;
    this.$btn = $('.btn.btn-xs.cab');
    this.$oMask = $('#mask_shadow');
    this.$oTitle = this.$elem.find('.title');
    this.$close = this.$oTitle.find('span');
    this.b_stop = true; // 防止重复点击
    this.opts = $.extend({}, this.defaults, options);
  };

  Plugin.prototype = {
    inital: function () { // 初始化
      var self = this;
      this.$elem.on('click', function () {
        return false;
      });
      this.$btn.on('click', function () {  
        self.popbox();

        self.b_stop = false;

        return false;
      });

      this.$close.on('click', function () {
        self.closePopbox();

        return false;
      });

      $(document.body).on('click', function () {
        self.closePopbox();
      });
    },

    popbox: function () { // 显示弹窗
      var self = this;
      // debugger;
      this.$oMask.show().animate({opacity: 1});
      this.$elem.show().animate({opacity: 1}, function () {
        self.b_stop = true;
      });
    },

    closePopbox: function () { // 关闭弹窗
      var self = this;

      if (this.b_stop) {
        this.$oMask.animate({opacity: 0,}, function () {
          $(this).hide();
        });
        this.$elem.animate({opacity: 0}, function () {
          $(this).hide();
        });
      }
    },
    constructor: Plugin
  };

  $.fn.popup = function (options) {
    var plugin = new Plugin(this, options);
    return plugin.inital();
  };
});