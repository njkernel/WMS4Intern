var a=0.6;
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
ctx.strokeStyle = '#337ab7';
ctx.lineWidth = 3*a;
ctx.shadowBlur = 5*a;
ctx.shadowColor = '#337ab7';
var milliseconds = 0;
var minutes = 0;
var hour = 0;
var date = "";
var ctxBack = canvas.getContext("2d");
var numBack = canvas.getContext("2d");
ctxBack.lineWidth = 1;
ctxBack.shadowBlur = 0;
ctxBack.shadowColor = '#F0F8FF';
function pageInit() {
    showTime();
    showBack();
    drawSecPin();
    drawMinPin();
    drawHouPin();
    setPoint();
    setNum();
}
function setNum() {
    numBack.save();
    numBack.translate(250*a, 250*a);
    numBack.beginPath();
    numBack.fillStyle = '#337ab7';
    numBack.font = "18px Helvetica";
    for (var i = 0; i < 60; i++) {
        if (i % 5 == 0) {
            numBack.lineWidth = 5*a;
            var xPoint = Math.sin(i * 6 * 2 * Math.PI / 360) * 195*a;
            var yPoint = -Math.cos(i * 6 * 2 * Math.PI / 360) * 195*a;
            numBack.fillText(i == 0 ? 12 : i / 5, i == 0 ? -15 : xPoint - 10, i == 0 ? -185 *a: i <= 30 ? yPoint + 5 : yPoint + 10);
        }
    }
    numBack.stroke();
    numBack.closePath();
    numBack.restore();
}
function drawSecPin() {
    ctxBack.save();
    ctxBack.translate(250*a, 250*a);
    ctxBack.rotate(milliseconds / 60 * 2 * Math.PI);
    ctxBack.beginPath();
    ctxBack.strokeStyle = '#337ab7';
    ctxBack.lineWidth = 1;
    ctxBack.lineJoin = "bevel";
    ctxBack.miterLimit = 10*a;
    ctxBack.moveTo(0, 30*a);
    ctxBack.lineTo(3*a, -175*a);
    ctxBack.lineTo(13*a, -165*a);
    ctxBack.lineTo(0, -210*a);
    ctxBack.lineTo(-13*a, -165*a);
    ctxBack.lineTo(-3*a, -175*a);
    ctxBack.lineTo(0, 30*a);
    ctxBack.stroke();
    ctxBack.closePath();
    ctxBack.restore();
}
function drawMinPin() {
    ctxBack.save();
    ctxBack.translate(250*a, 250*a);
    ctxBack.rotate(minutes * 6 * Math.PI / 180);
    ctxBack.beginPath();
    ctxBack.strokeStyle = '#337ab7';
    ctxBack.lineWidth = 1;
    ctxBack.lineJoin = "bevel";
    ctxBack.miterLimit = 10*a;
    ctxBack.moveTo(0, 20*a);
    ctxBack.lineTo(3*a, -145*a);
    ctxBack.lineTo(10, -135*a);
    ctxBack.lineTo(0, -180*a);
    ctxBack.lineTo(-10*a, -135*a);
    ctxBack.lineTo(-3*a, -145*a);
    ctxBack.lineTo(0, 20*a);
    ctxBack.stroke();
    ctxBack.closePath();
    ctxBack.restore();
}
function drawHouPin() {
    ctxBack.save();
    ctxBack.translate(250*a, 250*a);
    ctxBack.rotate(hour * 30 * Math.PI / 180);
    ctxBack.beginPath();
    ctxBack.strokeStyle = '#337ab7';
    ctxBack.lineWidth = 1;
    ctxBack.lineJoin = "bevel";
    ctxBack.miterLimit = 10*a;
    ctxBack.moveTo(0, 20*a);
    ctxBack.lineTo(3*a, -110*a);
    ctxBack.lineTo(10*a, -100*a);
    ctxBack.lineTo(0, -150*a);
    ctxBack.lineTo(-10*a, -100*a);
    ctxBack.lineTo(-3*a, -110*a);
    ctxBack.lineTo(0, 20*a);
    ctxBack.stroke();
    ctxBack.closePath();
    ctxBack.restore();
}
function setPoint() {
    ctxBack.beginPath();
    ctxBack.fillStyle = 'black';
    ctxBack.arc(250*a, 250*a, 3*a, 0, 2 * Math.PI);
    ctxBack.stroke();
}
function showBack() {
    for (var i = 0; i < 60; i++) {
        ctxBack.save();
        ctxBack.translate(250*a, 250*a);
        ctxBack.rotate(i / 60 * 2 * Math.PI);
        ctxBack.beginPath();
        ctxBack.strokeStyle = '#337ab7';
        ctxBack.moveTo(0, -250*a);
        ctxBack.lineWidth = i % 5 == 0 ? 5 : 2;
        ctxBack.lineTo(0, -230*a);
        ctxBack.stroke();
        ctxBack.closePath();
        ctxBack.restore();
    }
    ctxBack.beginPath();
    ctxBack.arc(250*a, 250*a, 230*a, 0, 2 * Math.PI);
    ctxBack.stroke();
}
function degToRad(degree) {
    var result;
    var factor = Math.PI / 180;
    if (degree == 0) {
        result = 270 * factor;
    } else {
        result = degree * factor;
    }
    return result;
}
function showTime() {
    var now = new Date();
    var today = now.toLocaleDateString();
    var time = now.toLocaleTimeString();
    var day = now.getDay();
    var hrs = now.getHours();
    var min = now.getMinutes();
    var sec = now.getSeconds();
    var mil = now.getMilliseconds();
    var smoothsec = sec + (mil / 1000);
    var smoothmin = min + (smoothsec / 60);
    var hours = hrs + (smoothmin / 60);
    milliseconds = smoothsec;
    minutes = smoothmin;
    hour = hours;
    switch (day) {
        case 1:
            date = '一'
            break;
        case 2:
            date = '二'
            break;
        case 3:
            date = '三'
            break;
        case 4:
            date = '四'
            break;
        case 5:
            date = '五'
            break;
        case 6:
            date = '六'
            break;
        case 0:
            date = '日'
            break;
    }
    gradient = ctx.createLinearGradient(250*a, 250*a, 5*a, 250*a, 250*a, 300*a);
    gradient.addColorStop(0, "#fff");
    gradient.addColorStop(1,"#ebebeb");
    ctx.fillStyle = gradient;
    ctx.fillRect(0, 0, 500*a, 500*a);
    ctx.beginPath();
    ctx.strokeStyle = '#337ab7';
    ctx.arc(250*a, 250*a, 215*a, degToRad(0), degToRad((hours * 30) - 90));
    ctx.stroke();
    ctx.beginPath();
    ctx.strokeStyle = '#337ab7';
    ctx.arc(250*a, 250*a, 220*a, degToRad(0), degToRad(smoothmin * 6 - 90));
    ctx.stroke();
    ctx.beginPath();
    ctx.strokeStyle = '#337ab7';
    ctx.arc(250*a, 250*a, 225*a, degToRad(0), degToRad(smoothsec * 6 - 90));
    ctx.stroke();
    ctx.font = "20px Helvetica Bold";
    ctx.fillStyle = '#337ab7';
    ctx.fillText(today + "/星期" + date, 150*a, 230*a);
    ctx.font = "20px Helvetica Bold";
    ctx.fillStyle = '#337ab7';
    ctx.fillText(time, 190*a, 280*a);
}
setInterval(pageInit, 50);