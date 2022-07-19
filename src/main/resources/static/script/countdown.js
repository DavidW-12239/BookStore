function fresh(){
    var myDate = new Date();
    var endtime = new Date(new Date(new Date().toLocaleDateString()).getTime()+24*60*60*1000-1);
    var nowtime = myDate;
    var leftsecond = parseInt((endtime.getTime() - nowtime.getTime())/1000);
    h=parseInt(leftsecond/3600);
    m=parseInt((leftsecond/60)%60);
    s=parseInt(leftsecond%60);
    if(h<10){
        h="0"+h;

    }
    if(m<10&&m>=0){
        m = "0"+m;
    }else if(m<0){
        m="00";
    }
    if(s<10&&s>=0){
        s = "0"+s;
    }else if(s<0){
        s="00";
    }
    document.getElementById('hour').innerHTML=h;
    document.getElementById('minute').innerHTML=m;
    document.getElementById('second').innerHTML=s;
    if(leftsecond<=0){
        document.getElementById('bot-box').style.display='block';
        document.getElementById('bot-box').style.background='url(img/code.jpg) no-repeat';
        document.getElementById('bot-box').innerHTML='end';
        clearInterval(sh);
    }
}
var sh=setInterval(fresh,1000);