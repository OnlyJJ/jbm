package com.lm.jbm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.lm.jbm.service.JmService;
import com.lm.jbm.thread.LoginThread;

public class RandomUtil {
	public static ConcurrentHashMap<String, Integer> nameMap = new ConcurrentHashMap<String, Integer>(512);
	public static ConcurrentHashMap<String, String> remarkMap = new ConcurrentHashMap<String, String>(512);
	public static ConcurrentHashMap<String, String> briMap = new ConcurrentHashMap<String, String>(512);
	public static final String[] ips = {
		"120.15.129.117",
		"120.15.129.118",
		"120.15.125.119",
		"120.9.125.123",
		"120.9.122.117",
		"120.15.144.155",
		"120.193.158.91",
		"120.193.252.166",
		"120.202.118.183",
		"120.229.126.123",
		"120.229.131.23",
		"120.236.173.94",
		"120.236.61.118",
		"120.239.162.172",
		"120.239.18.18",
		"120.3.163.13",
		"120.3.234.198",
		"120.37.40.148",
		"120.4.9.218",
		"120.85.87.212",
		"121.10.59.168",
		"121.17.117.114",
		"121.20.217.188",
		"121.207.17.21",
		"121.207.75.156",
		"121.22.243.145",
		"121.224.183.71",
		"121.226.252.23",
		"121.227.1.145",
		"121.236.119.29",
		"121.238.36.19",
		"121.32.193.27",
		"121.33.175.134",
		"121.33.33.132",
		"121.33.48.183",
		"121.35.202.24",
		"121.62.184.219",
		"121.62.220.78",
		"121.69.46.34",
		"122.143.20.17",
		"122.192.14.37",
		"122.195.136.22",
		"122.224.34.14",
		"122.224.52.136",
		"122.228.60.134",
		"122.233.110.159",
		"122.244.50.6",
		"122.70.145.182",
		"122.96.42.26",
		"122.97.178.214",
		"123.103.15.112",
		"123.115.99.159",
		"123.13.204.155",
		"123.135.243.119",
		"123.138.153.139",
		"123.138.228.169",
		"123.138.232.231",
		"123.147.244.134",
		"123.147.244.19",
		"123.147.244.191",
		"123.147.246.241",
		"123.147.250.144",
		"123.149.34.115",
		"123.149.86.139",
		"123.15.160.158",
		"123.158.205.163",
		"123.171.13.108",
		"123.179.253.146",
		"123.4.118.126",
		"123.52.219.174",
		"123.5.232.176",
		"123.7.30.221",
		"124.116.241.12",
		"124.126.224.217",
		"124.129.196.224",
		"124.130.85.217",
		"124.135.104.79",
		"124.160.153.57",
		"124.160.212.42",
		"124.160.215.31",
		"124.160.219.46",
		"124.163.231.182",
		"124.164.243.132",
		"124.165.143.72",
		"124.166.38.24",
		"124.200.106.95",
		"124.207.50.22",
		"124.227.55.97",
		"124.228.155.110",
		"124.228.19.22",
		"124.238.204.16",
		"124.239.251.14",
		"124.31.62.17",
		"124.72.51.18",
		"124.90.187.92",
		"125.39.30.15",
		"125.45.231.186",
		"125.65.222.21",
		"125.71.55.20",
		"125.72.45.112",
		"125.73.78.101",
		"125.73.93.129",
		"125.75.76.35",
		"125.76.128.122",
		"125.76.135.179",
		"125.79.67.4",
		"125.80.188.152",
		"125.82.186.14",
		"125.85.186.162",
		"125.85.186.24",
		"125.85.186.22",
		"125.91.49.146",
		"125.94.232.39",
		"139.189.155.142",
		"139.205.228.20",
		"139.206.193.30",
		"139.212.219.26",
		"139.214.99.41",
		"140.206.89.157",
		"140.240.27.72",
		"144.0.22.18",
		"153.34.21.35",
		"163.179.0.236",
		"171.105.232.213",
		"171.105.34.184",
		"171.106.2.73",
		"171.109.182.149",
		"171.111.92.132",
		"171.115.151.183",
		"171.11.72.19",
		"171.12.1.196",
		"171.12.3.22",
		"171.13.187.83",
		"171.221.143.169",
		"171.38.246.57",
		"171.40.144.26",
		"171.44.174.224",
		"171.8.18.18",
		"171.82.219.128",
		"175.12.144.23",
		"175.12.96.148",
		"175.146.37.122",
		"175.149.68.154",
		"175.15.144.113",
		"175.167.174.68",
		"175.167.58.170",
		"175.17.22.128",
		"175.19.130.11",
		"175.19.130.172",
		"175.19.51.175",
		"175.19.53.135",
		"175.19.54.185",
		"175.43.183.39",
		"180.103.220.11",
		"180.104.230.54",
		"180.126.145.167",
		"180.143.82.169",
		"180.156.41.151",
		"180.162.177.72",
		"180.230.36.194",
		"180.95.195.23",
		"181.92.139.66",
		"182.105.47.11",
		"182.114.5.188",
		"182.127.142.134",
		"182.139.208.55",
		"182.147.56.44",
		"182.151.166.174",
		"182.203.155.46",
		"182.207.216.161",
		"182.247.251.36",
		"182.38.140.120",
		"182.41.114.12",
		"182.45.29.71",
		"182.85.15.166",
		"182.90.221.144",
		"182.90.223.66",
		"182.97.184.220",
		"183.0.91.125",
		"183.150.36.77",
		"183.154.177.13",
		"183.157.160.14",
		"183.158.222.28",
		"183.162.3.21",
		"183.167.178.72",
		"183.167.211.111",
		"183.1.77.114",
		"183.197.43.27",
		"183.198.201.117",
		"183.198.34.15",
		"183.202.161.149",
		"183.202.208.90",
		"183.202.209.146",
		"183.202.209.61",
		"183.202.64.20",
		"183.204.45.80",
		"183.205.135.197",
		"183.205.140.66",
		"183.206.175.112",
		"183.208.28.10",
		"183.211.184.175",
		"183.212.144.128",
		"183.213.207.173",
		"183.214.111.143",
		"183.214.147.137",
		"183.214.190.120",
		"183.214.23.16",
		"183.2.200.15",
		"183.22.25.198",
		"183.223.149.117",
		"183.225.68.37",
		"183.226.14.19",
		"183.226.166.15",
		"183.234.194.130",
		"183.236.19.169",
		"183.236.19.74",
		"183.240.19.26",
		"183.240.20.25",
		"183.240.27.217",
		"183.245.221.23",
		"183.248.81.183",
		"183.35.203.41",
		"183.36.31.210",
		"183.39.53.12",
		"183.40.6.17",
		"183.50.98.182",
		"183.53.132.13",
		"183.95.51.63",
		"202.109.166.116",
		"202.109.166.119",
		"202.109.166.120",
		"209.35.30.210",
		"210.21.228.112",
		"210.21.228.112",
		"210.21.68.33",
		"211.142.199.185",
		"211.142.221.52",
		"211.94.234.148",
		"220.168.132.106",
		"220.171.138.11",
		"220.172.55.143",
		"220.175.19.204",
		"220.184.23.139",
		"220.195.64.52",
		"220.195.64.145",
		"220.195.65.174",
		"220.198.240.136",
		"220.202.152.178",
		"221.11.63.86",
		"221.13.63.114",
		"221.180.251.71",
		"221.192.179.130",
		"221.193.0.145",
		"221.193.193.31",
		"221.197.245.18",
		"221.203.194.87",
		"221.207.37.16",
		"221.207.37.141",
		"221.209.36.145",
		"221.211.86.46",
		"221.2.225.120",
		"221.7.7.43",
		"222.189.192.12",
		"222.216.23.143",
		"222.246.184.192",
		"222.38.200.18",
		"222.69.184.134",
		"222.79.238.123",
		"222.88.203.118",
		"222.92.1.194",
		"222.92.159.216",
		"223.10.141.24",
		"223.10.147.145",
		"223.104.12.228",
		"223.104.12.72",
		"223.104.15.217",
		"223.104.170.125",
		"223.104.170.46",
		"223.104.170.184",
		"223.104.17.222",
		"223.104.177.152",
		"223.104.189.225",
		"223.104.189.178",
		"223.104.22.127",
		"223.104.238.199",
		"223.104.24.156",
		"223.104.24.163",
		"223.104.255.165",
		"223.104.63.167",
		"223.104.63.238",
		"223.104.63.251",
		"223.104.94.143",
		"223.104.94.63",
		"223.104.95.44",
		"223.104.95.82",
		"223.112.202.132",
		"223.112.202.204",
		"223.113.11.162",
		"223.113.52.13",
		"223.11.45.134",
		"223.153.22.141",
		"223.155.170.12",
		"223.199.219.242",
		"223.208.105.33",
		"223.214.51.212",
		"223.215.82.232",
		"223.220.140.153",
		"223.220.140.55",
		"223.64.74.186",
		"223.73.135.84",
		"223.73.135.94",
		"223.73.238.214",
		"223.73.60.163",
		"223.73.60.27",
		"223.74.109.14",
		"223.74.34.17",
		"223.74.82.243",
		"223.74.82.53",
		"223.75.11.143",
		"223.79.82.217",
		"223.84.205.133",
		"223.86.232.6",
		"223.86.85.77",
		"223.89.189.173",
		"223.96.157.113",
		"223.97.132.217",
		"223.99.54.183",
		"223.99.57.162"
	};
	public static String[] remark = {"美美哒~~","么么哒!~~","不离不弃","永远爱你！","哈哈哈哈~","再见","明年会改变","不要不理我", "滚","真特么的曹蛋","有本事你别走","来啊，干","什么瘠薄玩意","大理司招生","千言万语",
			"晴儿美美的","小傻瓜","爱不够啊","心相随","你不走我就留","期待！！！","无形无影","天天睡懒觉，日日更健康","五十度","天哥不只是说说的，你看吧","大言不惭，本人都懒得屌你","真是煞笔，什么人都有","恶心到我了，啥x",
			"mt有真美女么？","大哥果然在很豪爽，哈哈哈哈","留下人来，你走开","那个妹子，哥哥好喜欢","新人我喜欢","别来烦我"};
	public static String[] one = {"趁虚而入","空山心雨","云淡风轻","青冘","朴亦","风软一江水","慵","孤星","十八里街禁","既然无缘","野爹",
			"大白牙生快","伯人心贤","南同学","清决","弹指丶红颜老","天与橙","网黑","好演技","浪人叔","黑市","念念不忘","元气鹿","呻吟启蒙师",
			"逢场作戏","永远的过客","服软","征骨岁月","高冷怪","榆西","有味叔","掏空左右","青袂宛约","后会无期","释怀","若即若離","花落成雨",
			"小情绪，难控制","终年不遇","鹿屿","枪手","撒哇嘀咔","滴·好人卡","你是个好人","一婊人才","抚菊圣手","借我一百块","摸胸算命","学弟°",
			"职业捐精户","浪荡先森","大龄男青年","一屁崩死你","本皇只要你"
			,"十分.喜欢","火星机器猫","骞骞","诸葛不亮","一梦庄周","北雅图","缘@起","如烟?勿忘我","十秒多重性格","撸啦撸啦咧ミ","你侧漏了！","此号已作废!~",
			"-騎士精神°","拒絕愛妹@","愛情、不過如此。","好了不見面。","寂寞是空虛的","淺淺の遺忘","孤獨病°","念挂V°","收皂角豆一光宇","?欢迎小情人?",
			"我为你偷桃","小游客。一个","微儿余生都是你","思“情”","宠韵儿之哔哔","??朵儿??小单","白色恋人〖耳朵〗","?宠夏上天","雅丽232",
			"、如烟苞米地","来生的一次回眸","只爱、嘉兴","一米阳??光","别玩、哥带你回家","薇薇、你是我的爱","抱歉、打扰","、缘分天空","尘哥^",
			
			};
	public static String[] two = {"未来不会来","心还没死透","何必执着","再见如陌","归无期","一别便不再见","趴趴树","初衷","心在流浪","错觉","好久不见°","奶凶"
			,"天屎","怪我了","大酋长","鲁汉","小明","韩少","凉凉","哎坡","男芳","青尤","瓜皮","杰哥","今非昔比","怂爷","柠萌","白日萌","锅盖头","晓A",
			"今晚毛毛雨","爱过·放手了","别问我来过","老大叔","碰个瓷","捡漏","疏通下水道","飞过","穷鸟","袍哥","果二","仞刃","大刀肩上扛","5米大砍刀","社会我瓜哥",
			"tian瓜","毒瘤","二杆子","高山炊烟","望·无痕","呜呜。。。","噗噗。。。","柯基","社会哈","名臣","洞察","管中窥豹·纹","大炸蟹","客官别走",
			"掌柜","咯名","清明·上·河图","日·久生情","可爱多","7仔","猛如虎","大力丸","20厘米","无名一☞","思念168","你的心只属于我","㊣企鹅㊣",
			"㊣人生若如初见㊣","大叔看戏的",
			"妞ヽ给爷来一个","乡、巴佬进城","鱼&七秒记忆","Xer儿的郭大伟","傻、成神?","MG來看看","?D?南?","?白云成神?","淡.定86","烧烤??舞",
			"情?小xin","℡走.雷、","吾爱雷爱吾","雷、微??天下","佛说、不归客??","L雷语馨愿","舞?浪迹天下"
	};
	public static String[] thr = {"大黑仔","我是谁","我在哪","好厉害","啊~疼疼","啊~爽爽","啊~不要",
			"啊~哎哟","啊~来吧","·啊~不来","啊·管你呢","嘿嘿·不黑","小·果粉","大·啊哥","随便·啦","你好呀·","我不好~·","哇塞·大","哇塞·深","哇塞·白","哇塞·。。",
			"厉害·哥","好随便·","欢迎你·","啊·欢迎我","嗯·不欢迎你","啊·到底欢不欢迎","不黑不黑","天天吃豆子","不要吧·","可以的啊·","蜜桃小忠粉",
			"阿四哥","55哥","十三哥","大基哥","看热闹·","那啥啊·","没啥货·","da敞亮","扯·犊子","干·哈咧","拉倒·吧","闹哪样@","忆念-Chen.","我不稀罕i",
			"大表姐·","咋整·","·嗯哪","墨·姬","毛·楞","磕`碜","咯`应","打`狼","得`巴","埋·汰","二牛子","☆安静?∫看戏的","☆安静?∫的思念","心跳的节奏","好艰难啊","Smileヾ独醉","蜜桃第一色独醉","敷衍、怎么演",};
	public static String[] fuhao = {"❤","☜","☝","★","☺","☀","☚","☟","☛","♦","☆","♈","♉",
			"❀","✿","ㅎ","ㅍ","ㅃ","ㅆ","㉵",
			"㉴","㈁","㈀","㈀","㈆","㈊","㊣","☯","✈"};
	public static String[] nogroup = {
			"惦·念","゛微微一笑","人老力不衰","夜跑不点灯","爱·久久","凝神","阿花别跑","強子","笑笑·","放手","邂逅","蜜桃用户","九夜°","芈山妖",
			"美颜相机","小沫°","你是个巨婴吧","秋裤男","不穿毛裤","小子艾",
			"大B哥","小范","从良了啊","目标50桃","台风来了","蛋碎了·","鸡毛掸子","我就来看看",
			"哇塞·好多","浪起来","浪里个浪","大傻子","IA耶`","控°","来`gan了","冰冰·举人","乐得清闲","一起嗨啊","无聊中。。","喵喵控",
			"大闸蟹","西毒·大愣子","安逸哦","破毛病","摸摸哒","么么·变·大","摸摸·变·大","摸摸·乖","大哥没人理","求带飞","库擦","毛腿","飞毛腿","好急默啊","改头换面",
			"折翼","闲了蛋疼","静观美女","只看不说话","随便转转","看~灰机","剁手","走过路过","H高了","大路茬子","野路子","随·变","冰儿好暖","有猿再见","烦。。。","M-萝卜",
			"喵奴","溜了溜了","花花对不起","我不是黑粉","露含","整没了","安静","放开那菇凉","你行你上","安尐","尛·","哎呦呦",
			"墨尐兮","゛尐兜兜","开森","加載中……","南子","皮球","傾城","24k·純爺們","如花你好","邪·",
			"找不着北了","蓝月亮°","嘿·来吧","尐持","星星","若惜〆","瘋了","前蓝","哴","山寨.","光灬年","丶浪","梦醒了",
			"蔸蔸゛","揮靃","蔓延","é魂爷","掱伈","灰烬","農村範","劇蔠R","执·唸","天黑晚安","左手","右手","无名指",
			"小雅","鹏灬鹏","彼岸花","心·锁","歎故人","离咒","島域","偏執","潮范","浪人","旧情","夢一场","情歌","窒息","厭世ˇ","空白·割",
			"淺笑°","平凡","路人乙","跑龙套","陌丶沫","辣条","七鱼","南蛮","坤.哥","萌轩.","颖宝♡","玲珑塔","初来匝道","故人",
			"徐大大@","苏陌","栾瑾","顾柒冉","陈默","小野仔","凯子","月牙","空白纸℡","招搖","符堙","清玄","来这渡劫了","墨尘","松子","慕笛",
			"龙呈宇","十里烟寂","清风·你们开心","U直上","我想静静","点墨","安泰","服э了","雨航","貝.乐·","短·路","莫邪","千缕","花开◎","醉了不愿醒","无忧","固·执",
			"祂·依恋","格子","老炮儿°","碎·瓦片","基熊","分叉","墨鱼仔","嘟嘟·快滴","滴·老人卡","滴·健身卡",
			"滴·打卡","滴·无聊","少侠·留种","空心","浅诺","fai荀","天南海北闯大江","幽美南极圈","真鸡儿累","摆渡人°",".執著",
			"゛范晨","藍几","大秋裤","冉丶","木瑾","沐亽","囧囧","千寻","藍°","小北","皇·妖",
			"口回囧","ら迷茫","柒柒.","蓝泡泡♡","不动如山圣如佛","鱼七主儿","苏果儿","暖暖·小刺","二十一.","暮惜哦~","小祖宗♡","烂好人.","小姐姐好看",
			"襟區","媥秇","界限","信仰。","ゞ累","毁诺","U果","Xx栗子","時光","〤霸道","堂主",
			"ぺ伤","羽化尘","碉·楼","向ㄖ","小懒","破伤风","樱花","殘钰","来了又走","蓝光1880","你懂的","喜提孤岛","中奖用户","男爵","铂爵",
			"我不是大神","悔·过","时光机","缘·粪","披头尸","矢哲","㊣大喇叭","＠小角色","鱼泡眼","◎單男◎","經紀人","蓝月","小鬼","隐身",
			"尐怪獣","有妖气","撕扯","桃☆友","逍灬遥","佌愛","骨云","漁哥","柠檬·一口闷","有你就是晴天","釋懷",
			"撩妹控","木槿","你看不见我","大鸡贼","冷雪","刀召","妖·怪","湿主","湿父","大湿兄","小湿妹","心海","莫·邪","颩·一样的男人","念·念","柒°年","忆の江南",
			"没▼毛病","整那啥","整就整呗","少年°","巨婴","那年又三年","过去已不再゜","冷〤夜","道貌岸然","大眼圈",
			"莫相离","久不遇″","我是谁","我在哪","他想干什么","♀伊雪","冷Se调","墨小邪","戒掉·剁手","小晴天","胸出没","故事与你","不念过去",
			"小花骨朵","言不由衷","小禽兽","爺·24K纯帅","旧人劫","时光凉薄","勿忘初心","菰獨守护","痴迷","废柴一人","南瓜",
			"加载中……","千城墨白","龟太气功波","北港初晴","久旧酒","Mr.伍","伊。冬","潇潇","凹+凸=日","良人","天涯","梓柒","烟凉",
			"未命名","奥特蛋","互撸娃","不二心","不将就","吃藕丑","情未了","维他命","司机等等我","开黑的","键盘侠","阿西八","抠脚·阿加西",
			"闷油瓶","故人叹","炸学校","独守心房","梦及深海","预约余生","好梦分你","明夕何夕","抗蛋走天涯","前门鼓鼓","咕咕",
			"孤影凭栏","旧梦难回","黑巷","社会人","十四","毒药","俗人","㊣经","黑名单","一抹淡然","孤岛别墅","木北","社会表哥","我有大表哥","表哥罩我的"
		};
	public static String[] year = {"1981-","1982-","1983-","1984-","1985-","1986-","1987-","1988-","1989-","1990-","1991-","1992-"};
	public static String[] mounth = {"01-","02-","03-","04-","05-","06-","07-","08-","09-","10-","11-","12-"};
	public static String[] day = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"
			,"19","20","21","22","23","24","25","26","27","28"};
	
	public static String getUserIp(String userId) {
		if(LoginThread.ipMap.containsKey(userId)) {
			return LoginThread.ipMap.get(userId);
		}
		String ip = getIp();
		if(ip != null) {
			LoginThread.ipMap.put(userId, ip);
		}
		return ip;
	}
	
	public static String getIp() {
		int index = new Random().nextInt(ips.length);
		return ips[index];
	}
	
	public static String[] getUserIds(String key) {
		String userId = PropertiesUtil.getValue(key);
		if(StringUtils.isNotEmpty(userId)) {
			String[] userIds = userId.split(",");
			return userIds;
		}
		return null;
	}

	
	public static String getListener() {
		String listener = PropertiesUtil.getValue("listener");
		if(StringUtils.isNotEmpty(listener)) {
			return listener;
		}
		return "";
	}
	
	public static String getRoomId() {
		String roomId = PropertiesUtil.getValue("roomId");
		if(StringUtils.isNotEmpty(roomId)) {
			String[] roomIds = roomId.split(",");
			int index = new Random().nextInt(roomIds.length);
			return roomIds[index];
		}
		return "";
	}
	
	public static String getPwd() {
		String pwd = PropertiesUtil.getValue("pwd");
		if(StringUtils.isNotEmpty(pwd)) {
			return pwd;
		}
		return "";
	}
	
	public static int getTotal() {
		int total = Integer.parseInt(PropertiesUtil.getValue("total"));
		return total;
	}
	
	public static int getRandom(int minValue,int maxValue)
	{
		int returnValue=minValue;
		new java.util.Random();
		returnValue=(int)((maxValue-minValue)*Math.random()+minValue);
		return returnValue;
	}
	
	/**
	 * 
	 * @param level1 1类用户数
	 * @param level2 2类用户数
	 * @param level3 。。。
	 * @param level4。。。
	 * @param level5。。。
	 * @return
	 */
	public static List<String> getGroupUserIds(int level0, int level1, int level4,int level5, int level6, int level7,int level8, int level9) {
		List<String> ret = new ArrayList<String>();
		if(level0 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(0);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level0) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!JmService.isPluck(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level0));
					index++;
				}
			}
		} 
		if(level1 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(1);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level1) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level1));
					index++;
				}
			}
		}
		if(level4 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(4);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level4) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level4));
					index++;
				}
			}
		} 
		if(level5 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(5);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level5) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level5));
					index++;
				}
			}
		}
		if(level6 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(6);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level6) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level6));
					index++;
				}
			}
		}
		if(level7 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(7);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level7) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level7));
					index++;
				}
			}
		}
		if(level8 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(8);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level8) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level8));
					index++;
				}
			}
		}
		if(level9 >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(9);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > level9) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(level9));
					index++;
				}
			}
		}
		return ret;
	}
	
	public static List<String> getFastPeachUserIds(int num) {
		List<String> ret = new ArrayList<String>();
		List<String> list = PropertiesUtil.getLevelUserIds(6);
		if(list != null && list.size() >0) {
			int index = 1;
			int size = list.size();
			Collections.shuffle(list);
			for(int i=0; i<size; i++) {
				if(index > num) {
					break;
				}
				String userId = list.get(i);
				if(JmService.peachMap.containsKey(userId)) {
					continue;
				}
				if(!JmService.isPluck(userId)) {
					continue;
				}
				if(!isTimeOut(userId)) {
					continue;
				}
				ret.add(userId);
				JmService.peachMap.put(userId, String.valueOf(6));
				index++;
			}
		}
		return ret;
	}
	
	public static List<String> getNoInroomUserIds(int num) {
		List<String> ret = new ArrayList<String>();
		if(num >0) {
			List<String> list = PropertiesUtil.getLevelUserIds(7);
			if(list != null && list.size() >0) {
				int index = 1;
				int size = list.size();
				Collections.shuffle(list);
				for(int i=0; i<size; i++) {
					if(index > num) {
						break;
					}
					String userId = list.get(i);
					if(JmService.peachMap.containsKey(userId)) {
						continue;
					}
					if(!JmService.isPluck(userId)) {
						continue;
					}
					if(!isTimeOut(userId)) {
						continue;
					}
					ret.add(userId);
					JmService.peachMap.put(userId, String.valueOf(7));
					index++;
				}
			}
		}
		return ret;
	}
	
	public static String getNickname(String userId) {
		int type = -1; // 默认不组合
		String name = "";
		boolean isRename = false; // 是否有重命名过
		if(nameMap.containsKey(userId)) {
			type = nameMap.get(userId);
			isRename = true;
		}
		if(isRename) {
			if(type == 0) { //        特殊符号 + 单个字 + 特殊符号
				type = getRandom(1, 7);
			} else if(type == 1) { // 特殊符号 + 单个字 + 特殊符号 + 两个字
				int[] types = {0,3,2,4,6,5};
				type = types[new Random().nextInt(types.length)];
			} else if(type == 2) { // 特殊符号 + 单个字 + 特殊符号 + 三个字
				int[] types = {0,3,1,4,6,5};
				type = types[new Random().nextInt(types.length)];
			} else if(type == 3) { // 特殊符号 + 两个字 + 特殊符号
				int[] types = {0,6,1,4,2,5};
				type = types[new Random().nextInt(types.length)];
			} else if(type == 4) { // 特殊符号 + 两个字  + 特殊符号 + 单个字
				int[] types = {0,3,1,5,6,2};
				type = types[new Random().nextInt(types.length)];
			} else if(type == 5) { // 特殊符号 + 三个字  + 特殊符号
				int[] types = {0,3,1,4,6,2};
				type = types[new Random().nextInt(types.length)];
			} else if(type == 6) { // 特殊符号 + 三个字  + 特殊符号 + 单个字
				int[] types = {0,3,1,5,2,4};
				type = types[new Random().nextInt(types.length)];
			} else {
				getRandom(0, 7);
			}
		} 
		name = getName(type);
		nameMap.put(userId, type);
		return name;
	}
	
	private static String getName(int type) {
		String name = "";
		if(type == 0) { //        特殊符号 + 单个字 + 特殊符号
			name = one[getRandom(1, one.length)];
		} else if(type == 1) { // 特殊符号 + 单个字 + 特殊符号 + 两个字
			name = one[getRandom(1, one.length)];
		} else if(type == 2) { // 特殊符号 + 单个字 + 特殊符号 + 三个字
			name = fuhao[getRandom(1, fuhao.length)];
		} else if(type == 3) { // 特殊符号 + 两个字 + 特殊符号
			name = two[getRandom(1, two.length)];
		} else if(type == 4) { // 特殊符号 + 两个字  + 特殊符号 + 单个字
			name = two[getRandom(1, two.length)];
		} else if(type == 5) { // 特殊符号 + 三个字  + 特殊符号
			name = thr[getRandom(1, thr.length)];
		} else if(type == 6) { // 特殊符号 + 三个字  + 特殊符号 + 单个字
			name = thr[getRandom(1, thr.length)];
		} else { // 特殊符号 + 不需要组合的 + 特殊符号
			name = nogroup[getRandom(1, nogroup.length)];
		}  
		return name;
	}
	
	/**
	 * 如果昵称重复，则再加一些符合
	 * @return
	 */
	public static String reSetNickname(String nickname) {
		return nickname + fuhao[getRandom(1, fuhao.length)];
	}
	
	public static String getRemark(String userId) {
		if(remarkMap.contains(userId)) {
			return "";
		}
		String rem = remark[getRandom(0,remark.length)];
		if(StringUtils.isEmpty(rem)) {
			rem = "暂无";
		}
		return rem;
	}
	
	public static String getBrithday(String userId) {
		if(briMap.contains(userId)) {
			return "";
		}
		String y = year[getRandom(0, year.length)];
		String m = mounth[getRandom(0, mounth.length)];
		String d = day[getRandom(0, day.length)];
		return y+m+d;
	}
	
	public static boolean isTimeOut(String userId) {
		boolean flag = true;
		if(JmService.pluckRecordMap.contains(userId)) {
			Date now = new Date();
			String time = JmService.pluckRecordMap.get(userId);
			try {
				if(StringUtils.isNotEmpty(time)) {
					Date record = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
					if(now.before(record)) {
						LogUtil.log.info("### 获取抢桃用户，该用户还在休息时间，不参与，userId：" + userId + "，到期时间：" + time);
						flag = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
