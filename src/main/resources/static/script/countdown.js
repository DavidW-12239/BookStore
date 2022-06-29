function fresh(){
    var endtime=new Date("2022/07/02,14:20:10");
    var nowtime=new Date();
    var leftsecond=parseInt((endtime.getTime() - nowtime.getTime())/1000);
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