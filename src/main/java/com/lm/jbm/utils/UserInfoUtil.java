package com.lm.jbm.utils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

public class UserInfoUtil {
	/**
	 * 账号-手机对应关系
	 */
	public static Map<String, JSONObject> MOBILE_MAP = null;

	/**
	 * 获取用户手机
	 */
	public static String getMobile(String userId) {
		if(MOBILE_MAP == null) {
			init();
		}
		JSONObject json =  MOBILE_MAP.get(userId);
		if(json != null) {
			return json.getString("mobile");
		}
		return "";
	}
	
	/**
	 * 获取用户ip
	 * @param userId
	 * @return
	 */
	public static String getIp(String userId) {
		if(MOBILE_MAP == null) {
			init();
		}
		JSONObject json =  MOBILE_MAP.get(userId);
		if(json != null) {
			return json.getString("ip");
		}
		return "";
	}
	
	/**
	 * 获取用户密码
	 * @param userId
	 * @return
	 */
	public static String getPwd(String userId) {
		if(MOBILE_MAP == null) {
			init();
		}
		JSONObject json =  MOBILE_MAP.get(userId);
		if(json != null) {
			return json.getString("pwd");
		}
		return "";
	}
	
	public static synchronized void init() {
		MOBILE_MAP = new ConcurrentHashMap<String, JSONObject>(256);
		MOBILE_MAP.put("21148047", JSONObject.parseObject("{\"ip\":\"117.136.80.72\", \"pwd\":\"147814\",\"mobile\":\"13578391027\"}"));
		MOBILE_MAP.put("21316502", JSONObject.parseObject("{\"ip\":\"223.90.143.185\", \"pwd\":\"zhang999\",\"mobile\":\"13653978367\"}"));
		MOBILE_MAP.put("21175913", JSONObject.parseObject("{\"ip\":\"117.136.4.250\", \"pwd\":\"liangze321\",\"mobile\":\"13665461165\"}"));
		MOBILE_MAP.put("21204420", JSONObject.parseObject("{\"ip\":\"1.30.4.108\", \"pwd\":\"131415926\",\"mobile\":\"15024951559\"}"));
		MOBILE_MAP.put("21354701", JSONObject.parseObject("{\"ip\":\"112.224.23.3\", \"pwd\":\"123456789\",\"mobile\":\"15610690255\"}"));
		MOBILE_MAP.put("21250635", JSONObject.parseObject("{\"ip\":\"119.162.24.47\", \"pwd\":\"likeooooo\",\"mobile\":\"15666779059\"}"));
		MOBILE_MAP.put("20967723", JSONObject.parseObject("{\"ip\":\"121.206.167.7\", \"pwd\":\"123456\",\"mobile\":\"15859868705\"}"));
		MOBILE_MAP.put("21225238", JSONObject.parseObject("{\"ip\":\"61.158.146.62\", \"pwd\":\"wrnm1314..\",\"mobile\":\"16638708492\"}"));
		MOBILE_MAP.put("21255980", JSONObject.parseObject("{\"ip\":\"223.107.55.248\", \"pwd\":\"581722\",\"mobile\":\"18796380668\"}"));
		MOBILE_MAP.put("21354739", JSONObject.parseObject("{\"ip\":\"120.201.247.69\", \"pwd\":\"9809180coco\",\"mobile\":\"19909861493\"}"));
		//10
		MOBILE_MAP.put("21251926", JSONObject.parseObject("{\"ip\":\"223.98.218.134\", \"pwd\":\"1846236599\",\"mobile\":\"15265893486\"}"));
		MOBILE_MAP.put("21260296", JSONObject.parseObject("{\"ip\":\"106.58.250.249\", \"pwd\":\"lm201314\",\"mobile\":\"15587168890\"}"));
		MOBILE_MAP.put("21202872", JSONObject.parseObject("{\"ip\":\"116.116.255.187\", \"pwd\":\"A151745\",\"mobile\":\"18947426720\"}"));
		MOBILE_MAP.put("21156413", JSONObject.parseObject("{\"ip\":\"39.130.92.65\", \"pwd\":\"zhangwei\",\"mobile\":\"13095328349\"}"));
		MOBILE_MAP.put("21250236", JSONObject.parseObject("{\"ip\":\"39.155.40.105\", \"pwd\":\"qweasdzxc\",\"mobile\":\"15134738378\"}"));
		MOBILE_MAP.put("21248402", JSONObject.parseObject("{\"ip\":\"202.97.253.102\", \"pwd\":\"asdasd\",\"mobile\":\"17704579177\"}"));
		MOBILE_MAP.put("21196139", JSONObject.parseObject("{\"ip\":\"111.49.159.109\", \"pwd\":\"pjx@103088\",\"mobile\":\"15309471863\"}"));
		MOBILE_MAP.put("21258476", JSONObject.parseObject("{\"ip\":\"116.10.226.168\", \"pwd\":\"qwerty123456\",\"mobile\":\"17376163774\"}"));
		MOBILE_MAP.put("21256076", JSONObject.parseObject("{\"ip\":\"101.246.133.166\", \"pwd\":\"783636\",\"mobile\":\"15942045221\"}"));
		MOBILE_MAP.put("21224945", JSONObject.parseObject("{\"ip\":\"124.238.72.228\", \"pwd\":\"82451213\",\"mobile\":\"15133706941\"}"));
		//20
		MOBILE_MAP.put("21250426", JSONObject.parseObject("{\"ip\":\"103.63.155.28\", \"pwd\":\"321653772\",\"mobile\":\"15124190591\"}"));
		MOBILE_MAP.put("21254674", JSONObject.parseObject("{\"ip\":\"59.44.235.123\", \"pwd\":\"ld547017412\",\"mobile\":\"17614304032\"}"));
		MOBILE_MAP.put("21137672", JSONObject.parseObject("{\"ip\":\"175.167.152.21\", \"pwd\":\"1988613\",\"mobile\":\"18040069235\"}"));
		MOBILE_MAP.put("21267346", JSONObject.parseObject("{\"ip\":\"223.89.220.77\", \"pwd\":\"huyamei\",\"mobile\":\"18238016768\"}"));
		MOBILE_MAP.put("20787348", JSONObject.parseObject("{\"ip\":\"117.136.57.197\", \"pwd\":\"zxcvbnm\",\"mobile\":\"18746147599\"}"));
		MOBILE_MAP.put("21160304", JSONObject.parseObject("{\"ip\":\"223.104.65.116\", \"pwd\":\"xyy878787\",\"mobile\":\"17880795668\"}"));
		MOBILE_MAP.put("21207116", JSONObject.parseObject("{\"ip\":\"117.136.57.239\", \"pwd\":\"a1234567890\",\"mobile\":\"18345731591\"}"));
		MOBILE_MAP.put("21258780", JSONObject.parseObject("{\"ip\":\"112.43.25.31\", \"pwd\":\"cyg496976\",\"mobile\":\"13899929169\"}"));
		MOBILE_MAP.put("21256098", JSONObject.parseObject("{\"ip\":\"223.89.220.81\", \"pwd\":\"tang123456\",\"mobile\":\"15517478683\"}"));
		MOBILE_MAP.put("21248096", JSONObject.parseObject("{\"ip\":\"223.104.178.235\", \"pwd\":\"111111\",\"mobile\":\"18247592896\"}"));
		//30
		MOBILE_MAP.put("21264707", JSONObject.parseObject("{\"ip\":\"117.155.0.101\", \"pwd\":\"1920134087\",\"mobile\":\"18772973391\"}"));
		MOBILE_MAP.put("21246925", JSONObject.parseObject("{\"ip\":\"112.96.192.244\", \"pwd\":\"2000100574\",\"mobile\":\"13226772402\"}"));
		MOBILE_MAP.put("21267513", JSONObject.parseObject("{\"ip\":\"220.176.141.66\", \"pwd\":\"wwwsjxjkxk.\",\"mobile\":\"13437069034\"}"));
		MOBILE_MAP.put("21270501", JSONObject.parseObject("{\"ip\":\"222.213.182.9\", \"pwd\":\"123456cbd\",\"mobile\":\"15284894808\"}"));
		MOBILE_MAP.put("21195870", JSONObject.parseObject("{\"ip\":\"218.95.15.177\", \"pwd\":\"20031229xhj\",\"mobile\":\"17779772423\"}"));
		MOBILE_MAP.put("21245331", JSONObject.parseObject("{\"ip\":\"180.123.48.222\", \"pwd\":\"cheng13382695496\",\"mobile\":\"13382695496\"}"));
		MOBILE_MAP.put("21273560", JSONObject.parseObject("{\"ip\":\"223.104.63.179\", \"pwd\":\"123456\",\"mobile\":\"18398900332\"}"));
		MOBILE_MAP.put("21173630", JSONObject.parseObject("{\"ip\":\"223.98.101.159\", \"pwd\":\"aylp090525\",\"mobile\":\"19853923850\"}"));
		MOBILE_MAP.put("21254807", JSONObject.parseObject("{\"ip\":\"223.104.24.77\", \"pwd\":\"179770\",\"mobile\":\"18375239108\"}"));
		MOBILE_MAP.put("21189760", JSONObject.parseObject("{\"ip\":\"223.104.31.117\", \"pwd\":\"123456\",\"mobile\":\"18779415732\"}"));
		//40
		MOBILE_MAP.put("21249711", JSONObject.parseObject("{\"ip\":\"223.98.176.242\", \"pwd\":\"han8931023\",\"mobile\":\"15066130043\"}"));
		MOBILE_MAP.put("21281661", JSONObject.parseObject("{\"ip\":\"36.106.244.249\", \"pwd\":\"al990201\",\"mobile\":\"13299960289\"}"));
		MOBILE_MAP.put("20984389", JSONObject.parseObject("{\"ip\":\"223.67.6.74\", \"pwd\":\"sunhai5211314\",\"mobile\":\"15850899079\"}"));
		MOBILE_MAP.put("21264497", JSONObject.parseObject("{\"ip\":\"112.9.126.207\", \"pwd\":\"qw12345\",\"mobile\":\"15194232221\"}"));
		MOBILE_MAP.put("21248407", JSONObject.parseObject("{\"ip\":\"242.141.185\", \"pwd\":\"duguagzhi.123\",\"mobile\":\"13855772530\"}"));
		MOBILE_MAP.put("21253764", JSONObject.parseObject("{\"ip\":\"123.13.71.92\", \"pwd\":\"g873564\",\"mobile\":\"15238873564\"}"));
		MOBILE_MAP.put("21257258", JSONObject.parseObject("{\"ip\":\"110.252.4.225\", \"pwd\":\"huna88888\",\"mobile\":\"18633721982\"}"));
		MOBILE_MAP.put("21201185", JSONObject.parseObject("{\"ip\":\"218.26.55.162\", \"pwd\":\"18635193931\",\"mobile\":\"18635193931\"}"));
		MOBILE_MAP.put("21229684", JSONObject.parseObject("{\"ip\":\"42.49.0.23\", \"pwd\":\"1243297267\",\"mobile\":\"18274386411\"}"));
		MOBILE_MAP.put("21282096", JSONObject.parseObject("{\"ip\":\"220.195.67.50\", \"pwd\":\"503254\",\"mobile\":\"13848237264\"}"));
		//50
		MOBILE_MAP.put("20971336", JSONObject.parseObject("{\"ip\":\"120.224.230.162\", \"pwd\":\"asdf123456\",\"mobile\":\"15321125080\"}"));
		MOBILE_MAP.put("21286017", JSONObject.parseObject("{\"ip\":\"211.140.98.234\", \"pwd\":\"jun641442190\",\"mobile\":\"17602005497\"}"));
		MOBILE_MAP.put("21279166", JSONObject.parseObject("{\"ip\":\"120.227.108.35\", \"pwd\":\"yy200453\",\"mobile\":\"17872800529\"}"));
		MOBILE_MAP.put("21259246", JSONObject.parseObject("{\"ip\":\"180.155.138.244\", \"pwd\":\"jack1987zippo\",\"mobile\":\"15601822257\"}"));
		MOBILE_MAP.put("21286148", JSONObject.parseObject("{\"ip\":\"125.73.91.120\", \"pwd\":\"13078050940abcd\",\"mobile\":\"18878225215\"}"));
		MOBILE_MAP.put("21225240", JSONObject.parseObject("{\"ip\":\"223.104.35.182\", \"pwd\":\"qwertyuiop\",\"mobile\":\"18325981447\"}"));
		MOBILE_MAP.put("20984710", JSONObject.parseObject("{\"ip\":\"60.26.233.228\", \"pwd\":\"12345687\",\"mobile\":\"15822545186\"}"));
		MOBILE_MAP.put("21268465", JSONObject.parseObject("{\"ip\":\"116.26.106.180\", \"pwd\":\"1234567890\",\"mobile\":\"18902736110\"}"));
		MOBILE_MAP.put("21249274", JSONObject.parseObject("{\"ip\":\"171.126.123.55\", \"pwd\":\"123456\",\"mobile\":\"18635671628\"}"));
		MOBILE_MAP.put("21256447", JSONObject.parseObject("{\"ip\":\"111.121.67.246\", \"pwd\":\"fang051219\",\"mobile\":\"18985409539\"}"));
		//60
		MOBILE_MAP.put("21274522", JSONObject.parseObject("{\"ip\":\"117.181.142.192\", \"pwd\":\"zxcvbnm123456789\",\"mobile\":\"13558415126\"}"));
		MOBILE_MAP.put("21256085", JSONObject.parseObject("{\"ip\":\"223.72.59.101\", \"pwd\":\"lilei967086\",\"mobile\":\"13261967086\"}"));
		MOBILE_MAP.put("21264434", JSONObject.parseObject("{\"ip\":\"117.136.66.72\", \"pwd\":\"shenjie\",\"mobile\":\"15262153818\"}"));
		MOBILE_MAP.put("20979830", JSONObject.parseObject("{\"ip\":\"1.182.115.171\", \"pwd\":\"1238djdjv\",\"mobile\":\"18548617335\"}"));
		MOBILE_MAP.put("21277492", JSONObject.parseObject("{\"ip\":\"113.200.204.221\", \"pwd\":\"12345678\",\"mobile\":\"17868625075\"}"));
		MOBILE_MAP.put("21152329", JSONObject.parseObject("{\"ip\":\"117.136.89.215\", \"pwd\":\"lzx911012\",\"mobile\":\"15807354135\"}"));
		MOBILE_MAP.put("21256076", JSONObject.parseObject("{\"ip\":\"101.246.133.166\", \"pwd\":\"783636\",\"mobile\":\"15942045221\"}"));
		MOBILE_MAP.put("21224945", JSONObject.parseObject("{\"ip\":\"124.238.72.228\", \"pwd\":\"82451213\",\"mobile\":\"15133706941\"}"));
		MOBILE_MAP.put("21137672", JSONObject.parseObject("{\"ip\":\"175.167.152.21\", \"pwd\":\"1988613\",\"mobile\":\"18040069235\"}"));
		MOBILE_MAP.put("21269288", JSONObject.parseObject("{\"ip\":\"182.90.222.252\", \"pwd\":\"20051227iop\",\"mobile\":\"18177103123\"}"));
		MOBILE_MAP.put("21187104", JSONObject.parseObject("{\"ip\":\"27.192.72.139\", \"pwd\":\"123456789\",\"mobile\":\"15163652985\"}"));
		//70
		MOBILE_MAP.put("21228542", JSONObject.parseObject("{\"ip\":\"117.136.28.246\", \"pwd\":\"123456\",\"mobile\":\"18809603976\"}"));
		MOBILE_MAP.put("21298387", JSONObject.parseObject("{\"ip\":\"58.22.113.169\", \"pwd\":\"123456789z\",\"mobile\":\"15506986668\"}"));
		MOBILE_MAP.put("21284103", JSONObject.parseObject("{\"ip\":\"123.139.47.144\", \"pwd\":\"123456\",\"mobile\":\"15029247641\"}"));
		MOBILE_MAP.put("21293126", JSONObject.parseObject("{\"ip\":\"223.104.21.22\", \"pwd\":\"hu123456\",\"mobile\":\"15281405940\"}"));
		MOBILE_MAP.put("21206748", JSONObject.parseObject("{\"ip\":\"42.234.44.39\", \"pwd\":\"xx123456\",\"mobile\":\"15993775128\"}"));
		MOBILE_MAP.put("21278237", JSONObject.parseObject("{\"ip\":\"183.198.245.163\", \"pwd\":\"15533577811a\",\"mobile\":\"17717771202\"}"));
		MOBILE_MAP.put("21268380", JSONObject.parseObject("{\"ip\":\"24.164.238.25\", \"pwd\":\"qwerty\",\"mobile\":\"15135681930\"}"));
		MOBILE_MAP.put("21296480", JSONObject.parseObject("{\"ip\":\"117.188.209.139\", \"pwd\":\"724443\",\"mobile\":\"18216950724\"}"));
		MOBILE_MAP.put("21189823", JSONObject.parseObject("{\"ip\":\"36.157.56.242\", \"pwd\":\"NN13973081794\",\"mobile\":\"18627485633\"}"));
		MOBILE_MAP.put("21295840", JSONObject.parseObject("{\"ip\":\"113.77.223.209\", \"pwd\":\"lgq123456\",\"mobile\":\"18002994652\"}"));
		//80
		MOBILE_MAP.put("21245298", JSONObject.parseObject("{\"ip\":\"171.210.123.173\", \"pwd\":\"lizhengleoo\",\"mobile\":\"18990377637\"}"));
		MOBILE_MAP.put("21227048", JSONObject.parseObject("{\"ip\":\"221.6.140.162\", \"pwd\":\"090522\",\"mobile\":\"18762661012\"}"));
		MOBILE_MAP.put("21289739", JSONObject.parseObject("{\"ip\":\"183.27.158.15\", \"pwd\":\"hdm1986218\",\"mobile\":\"15360416487\"}"));
		MOBILE_MAP.put("21258622", JSONObject.parseObject("{\"ip\":\"112.41.156.72\", \"pwd\":\"xiaogang4603474\",\"mobile\":\"15041009959\"}"));
		MOBILE_MAP.put("21255295", JSONObject.parseObject("{\"ip\":\"106.41.166.138\", \"pwd\":\"cgj666\",\"mobile\":\"15843265628\"}"));
		MOBILE_MAP.put("21279239", JSONObject.parseObject("{\"ip\":\"42.92.160.82\", \"pwd\":\"18093155004\",\"mobile\":\"18093155004\"}"));
		MOBILE_MAP.put("21299063", JSONObject.parseObject("{\"ip\":\"113.78.214.107\", \"pwd\":\"5990214LYX\",\"mobile\":\"13415276327\"}"));
		MOBILE_MAP.put("21305015", JSONObject.parseObject("{\"ip\":\"60.10.44.99\", \"pwd\":\"qxl911163\",\"mobile\":\"18713817764\"}"));
		MOBILE_MAP.put("21304979", JSONObject.parseObject("{\"ip\":\"183.138.122.206\", \"pwd\":\"731031\",\"mobile\":\"13806532991\"}"));
		MOBILE_MAP.put("21290171", JSONObject.parseObject("{\"ip\":\"58.48.51.250\", \"pwd\":\"cui177263\",\"mobile\":\"13223729985\"}"));
		//90
		MOBILE_MAP.put("21248228", JSONObject.parseObject("{\"ip\":\"119.80.106.234\", \"pwd\":\"935914631.0\",\"mobile\":\"18810929596\"}"));
		MOBILE_MAP.put("21273679", JSONObject.parseObject("{\"ip\":\"220.174.21.116\", \"pwd\":\"S13976421173\",\"mobile\":\"17700988772\"}"));
		MOBILE_MAP.put("21279659", JSONObject.parseObject("{\"ip\":\"218.61.24.65\", \"pwd\":\"2485586838\",\"mobile\":\"18624590186\"}"));
		MOBILE_MAP.put("21318926", JSONObject.parseObject("{\"ip\":\"183.161.195.42\", \"pwd\":\"yz495581594\",\"mobile\":\"15357417175\"}"));
		MOBILE_MAP.put("21308070", JSONObject.parseObject("{\"ip\":\"112.97.166.67\", \"pwd\":\"lxy520\",\"mobile\":\"17375647029\"}"));
		MOBILE_MAP.put("21255091", JSONObject.parseObject("{\"ip\":\"112.38.59.40\", \"pwd\":\"chenchao123\",\"mobile\":\"15063546259\"}"));
		MOBILE_MAP.put("21322605", JSONObject.parseObject("{\"ip\":\"218.69.54.42\", \"pwd\":\"sifuxian\",\"mobile\":\"13602140900\"}"));
		MOBILE_MAP.put("21315575", JSONObject.parseObject("{\"ip\":\"223.98.210.30\", \"pwd\":\"000001995...\",\"mobile\":\"18560531390\"}"));
		MOBILE_MAP.put("21309360", JSONObject.parseObject("{\"ip\":\"220.195.64.133\", \"pwd\":\"qaz2008\",\"mobile\":\"17548017291\"}"));
		MOBILE_MAP.put("21284935", JSONObject.parseObject("{\"ip\":\"117.188.40.146\", \"pwd\":\"574575\",\"mobile\":\"15985313313\"}"));
		//100
		MOBILE_MAP.put("21279455", JSONObject.parseObject("{\"ip\":\"111.142.76.186\", \"pwd\":\"aswqed\",\"mobile\":\"13375971587\"}"));
		MOBILE_MAP.put("21257351", JSONObject.parseObject("{\"ip\":\"223.74.180.115\", \"pwd\":\"641012MY\",\"mobile\":\"15817682754\"}"));
		MOBILE_MAP.put("21280744", JSONObject.parseObject("{\"ip\":\"113.115.59.205\", \"pwd\":\"wu123456\",\"mobile\":\"13317896508\"}"));
		MOBILE_MAP.put("21265916", JSONObject.parseObject("{\"ip\":\"182.127.9.17\", \"pwd\":\"caigang1234\",\"mobile\":\"13703705237\"}"));
		MOBILE_MAP.put("21306760", JSONObject.parseObject("{\"ip\":\"113.228.250.78\", \"pwd\":\"123qwe\",\"mobile\":\"13940767352\"}"));
		MOBILE_MAP.put("21281571", JSONObject.parseObject("{\"ip\":\"117.136.90.133\", \"pwd\":\"wang2256\",\"mobile\":\"17535126335\"}"));
		MOBILE_MAP.put("21304386", JSONObject.parseObject("{\"ip\":\"222.215.161.233\", \"pwd\":\"qg12345678\",\"mobile\":\"18113325801\"}"));
		MOBILE_MAP.put("21309737", JSONObject.parseObject("{\"ip\":\"113.45.98.19\", \"pwd\":\"qazwsx123\",\"mobile\":\"15131725835\"}"));
		MOBILE_MAP.put("21285016", JSONObject.parseObject("{\"ip\":\"14.204.67.134\", \"pwd\":\"biao58420184\",\"mobile\":\"13385748838\"}"));
		MOBILE_MAP.put("21312776", JSONObject.parseObject("{\"ip\":\"171.212.193.165\", \"pwd\":\"zhangting123.\",\"mobile\":\"13438386436\"}"));
		//110
		MOBILE_MAP.put("21305766", JSONObject.parseObject("{\"ip\":\"111.194.44.10\", \"pwd\":\"jiang201406\",\"mobile\":\"18262274687\"}"));
		MOBILE_MAP.put("21308896", JSONObject.parseObject("{\"ip\":\"223.89.186.154\", \"pwd\":\"222333a\",\"mobile\":\"18626394020\"}"));
		MOBILE_MAP.put("21283580", JSONObject.parseObject("{\"ip\":\"36.4.167.134\", \"pwd\":\"123456\",\"mobile\":\"18959991952\"}"));
		MOBILE_MAP.put("21258929", JSONObject.parseObject("{\"ip\":\"113.115.54.72\", \"pwd\":\"123456\",\"mobile\":\"17728038474\"}"));
		MOBILE_MAP.put("21279461", JSONObject.parseObject("{\"ip\":\"115.53.141.149\", \"pwd\":\"hgc2018zxc\",\"mobile\":\"13889181473\"}"));
		MOBILE_MAP.put("21286028", JSONObject.parseObject("{\"ip\":\"183.93.123.49\", \"pwd\":\"2611548\",\"mobile\":\"18771175788\"}"));
		MOBILE_MAP.put("21249714", JSONObject.parseObject("{\"ip\":\"223.104.97.156\", \"pwd\":\"142536\",\"mobile\":\"13797819256\"}"));
		MOBILE_MAP.put("21309827", JSONObject.parseObject("{\"ip\":\"117.136.79.43\", \"pwd\":\"123321\",\"mobile\":\"15818150628\"}"));
		MOBILE_MAP.put("21283136", JSONObject.parseObject("{\"ip\":\"39.128.70.119\", \"pwd\":\"hz123123\",\"mobile\":\"15287701527\"}"));
		MOBILE_MAP.put("21304574", JSONObject.parseObject("{\"ip\":\"219.139.168.38\", \"pwd\":\"199388\",\"mobile\":\"15926925576\"}"));
		//120
		MOBILE_MAP.put("21289552", JSONObject.parseObject("{\"ip\":\"117.32.129.174\", \"pwd\":\"ac870312\",\"mobile\":\"13691049877\"}"));
		MOBILE_MAP.put("21315483", JSONObject.parseObject("{\"ip\":\"218.25.123.250\", \"pwd\":\"Zllnn13019302240\",\"mobile\":\"18624010296\"}"));
		MOBILE_MAP.put("21329854", JSONObject.parseObject("{\"ip\":\"180.127.215.125\", \"pwd\":\"liurui666.\",\"mobile\":\"17714146986\"}"));
		MOBILE_MAP.put("21296571", JSONObject.parseObject("{\"ip\":\"120.36.175.212\", \"pwd\":\"15960629828\",\"mobile\":\"15960629828\"}"));
		MOBILE_MAP.put("21335419", JSONObject.parseObject("{\"ip\":\"118.29.230.63\", \"pwd\":\"bx830124\",\"mobile\":\"18698168295\"}"));
		MOBILE_MAP.put("21324200", JSONObject.parseObject("{\"ip\":\"206.189.123.120\", \"pwd\":\"cd1chzhy\",\"mobile\":\"13980746177\"}"));
		MOBILE_MAP.put("21296762", JSONObject.parseObject("{\"ip\":\"223.104.170.114\", \"pwd\":\"123456yyh\",\"mobile\":\"18702646783\"}"));
		MOBILE_MAP.put("21282963", JSONObject.parseObject("{\"ip\":\"183.236.41.146\", \"pwd\":\"a19867200\",\"mobile\":\"13670253085\"}"));
		MOBILE_MAP.put("21339343", JSONObject.parseObject("{\"ip\":\"114.96.250.56\", \"pwd\":\"aaa12345\",\"mobile\":\"13063199935\"}"));
		MOBILE_MAP.put("21332650", JSONObject.parseObject("{\"ip\":\"117.166.22.76\", \"pwd\":\"1298906856\",\"mobile\":\"17379209025\"}"));
		//130
		MOBILE_MAP.put("21339505", JSONObject.parseObject("{\"ip\":\"223.104.63.248\", \"pwd\":\"xinyan11201\",\"mobile\":\"15007692315\"}"));
		MOBILE_MAP.put("21257585", JSONObject.parseObject("{\"ip\":\"39.181.143.6\", \"pwd\":\"123456\",\"mobile\":\"15752468485\"}"));
		MOBILE_MAP.put("21310119", JSONObject.parseObject("{\"ip\":\"118.120.116.74\", \"pwd\":\"y8989502\",\"mobile\":\"18818993081\"}"));
		MOBILE_MAP.put("21246857", JSONObject.parseObject("{\"ip\":\"117.182.191.41\", \"pwd\":\"0852134679\",\"mobile\":\"13737280207\"}"));
		MOBILE_MAP.put("21312795", JSONObject.parseObject("{\"ip\":\"114.249.52.143\", \"pwd\":\"223456\",\"mobile\":\"13121794226\"}"));
		MOBILE_MAP.put("21308040", JSONObject.parseObject("{\"ip\":\"36.157.224.5\", \"pwd\":\"1314520abcd\",\"mobile\":\"15874232337\"}"));
		MOBILE_MAP.put("21289159", JSONObject.parseObject("{\"ip\":\"218.19.214.190\", \"pwd\":\"qwertyuiop\",\"mobile\":\"13724146216\"}"));
		MOBILE_MAP.put("21337571", JSONObject.parseObject("{\"ip\":\"116.26.51.243\", \"pwd\":\"s662828g\",\"mobile\":\"18929671358\"}"));
		MOBILE_MAP.put("21339692", JSONObject.parseObject("{\"ip\":\"211.94.233.239\", \"pwd\":\"xue123456789\",\"mobile\":\"16622067276\"}"));
		MOBILE_MAP.put("21300372", JSONObject.parseObject("{\"ip\":\"61.140.255.84\", \"pwd\":\"8975667cjm\",\"mobile\":\"15813029267\"}"));
		//140
		MOBILE_MAP.put("21352755", JSONObject.parseObject("{\"ip\":\"49.95.57.171\", \"pwd\":\"153647356\",\"mobile\":\"18013639397\"}"));
		MOBILE_MAP.put("21320045", JSONObject.parseObject("{\"pwd\":\"wb147258\",\"mobile\":\"15144181292\" ,\"ip\":\"117.136.58.68\"}"));
		MOBILE_MAP.put("21318041", JSONObject.parseObject("{\"pwd\":\"2249324839\",\"mobile\":\"13709649171\" , \"ip\":\"120.239.46.76\"}"));
		MOBILE_MAP.put("21322957", JSONObject.parseObject("{\"pwd\":\"of2016\",\"mobile\":\"18770060279\" , \"ip\":\"223.104.170.186\"}"));
		MOBILE_MAP.put("21295524", JSONObject.parseObject("{\"pwd\":\"hl15123397990188\",\"mobile\":\"13145919734\", \"ip\":\"106.43.210.25\"}"));
		MOBILE_MAP.put("21309525", JSONObject.parseObject("{\"pwd\":\"123456\",\"mobile\":\"17731268136\"  ,\"ip\":\"27.186.174.50\"}"));
		MOBILE_MAP.put("21319293", JSONObject.parseObject("{\"pwd\":\"110528gc\",\"mobile\":\"18201801420\"  ,\"ip\":\"183.194.158.189\"}"));
		MOBILE_MAP.put("21275878", JSONObject.parseObject("{\"pwd\":\"z991223521\",\"mobile\":\"15210472060\" , \"ip\":\"111.196.208.251\"}"));
		MOBILE_MAP.put("21328365", JSONObject.parseObject("{\"pwd\":\"Pf121388\",\"mobile\":\"18763460190\" , \"ip\":\"112.224.17.131\"}"));
		MOBILE_MAP.put("21348082", JSONObject.parseObject("{\"pwd\":\"lihang11302534\",\"mobile\":\"17681023599\" , \"ip\":\"112.122.121.164\"}"));
		//150
		MOBILE_MAP.put("21254658", JSONObject.parseObject("{\"pwd\":\"103818\",\"mobile\":\"17633746679\" , \"ip\":\"61.158.148.87\"}"));
		MOBILE_MAP.put("21260435", JSONObject.parseObject("{\"pwd\":\"20020908\",\"mobile\":\"15531068771\" ,\"ip\":\"120.9.201.181\"}"));
		MOBILE_MAP.put("21319701", JSONObject.parseObject("{\"pwd\":\"my17603444471\",\"mobile\":\"17603444471\",\"ip\":\"118.74.226.73\"}"));
		MOBILE_MAP.put("21348156", JSONObject.parseObject("{\"pwd\":\"jiayou841853961\",\"mobile\":\"18265920286\",\"ip\":\"27.197.15.119\"}"));
		MOBILE_MAP.put("21342760", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"18146617606\"  ,\"ip\":\"182.97.79.0\"}"));
		MOBILE_MAP.put("21350334", JSONObject.parseObject("{\"pwd\":\"tao1314guo\",\"mobile\":\"19982756063\"  ,\"ip\":\"171.210.204.241\"}"));
		MOBILE_MAP.put("21329783", JSONObject.parseObject("{\"pwd\":\"qwerty\",\"mobile\":\"15639106412\"  ,\"ip\":\"182.115.251.33\"}"));
		MOBILE_MAP.put("21337573", JSONObject.parseObject("{\"pwd\":\"123456\",\"mobile\":\"18026679855\"  ,\"ip\":\"219.131.13.167\"}"));
		MOBILE_MAP.put("21320881", JSONObject.parseObject("{\"pwd\":\"fghgftiig\",\"mobile\":\"13283867186\"  ,\"ip\":\"39.150.207.74\"}"));
		MOBILE_MAP.put("21350745", JSONObject.parseObject("{\"pwd\":\"wdd5201314\",\"mobile\":\"13158162816\"  ,\"ip\":\"221.13.63.152\"}"));
		//160
		MOBILE_MAP.put("21342045", JSONObject.parseObject("{\"pwd\":\"15115957717\",\"mobile\":\"18573915375\"  ,\"ip\":\"119.39.76.53\"}"));
		MOBILE_MAP.put("21306601", JSONObject.parseObject("{\"pwd\":\"88888888\",\"mobile\":\"15172979454\",\"ip\":\"171.115.180.251\"}"));
		MOBILE_MAP.put("21351474", JSONObject.parseObject("{\"pwd\":\"a123456\",\"mobile\":\"13887958605\",\"ip\":\"39.128.187.35\"}"));
		MOBILE_MAP.put("21294814", JSONObject.parseObject("{\"pwd\":\"19950620x\",\"mobile\":\"17719938174\",\"ip\":\"112.17.241.21\"}"));
		MOBILE_MAP.put("21352637", JSONObject.parseObject("{\"pwd\":\"qz168979\",\"mobile\":\"18232570868\",\"ip\":\"117.188.61.85\"}"));
		MOBILE_MAP.put("21275449", JSONObject.parseObject("{\"pwd\":\"zh201617\",\"mobile\":\"13758659782\",\"ip\":\"117.136.73.178\"}"));
		MOBILE_MAP.put("21278803", JSONObject.parseObject("{\"pwd\":\"asd123456\",\"mobile\":\"18309868823\",\"ip\":\"117.179.188.41\"}"));
		MOBILE_MAP.put("21322541", JSONObject.parseObject("{\"pwd\":\"zs19980608\",\"mobile\":\"18287053524\" ,\"ip\":\"223.104.145.0\"}"));
		MOBILE_MAP.put("21263997", JSONObject.parseObject("{\"pwd\":\"lsk13969956951\",\"mobile\":\"15163965728\",\"ip\":\"10.238.112.166\"}"));
		MOBILE_MAP.put("21341954", JSONObject.parseObject("{\"pwd\":\"Asd233456\",\"mobile\":\"18713187777\" ,\"ip\":\"120.10.46.53\"}"));
		//170
		MOBILE_MAP.put("21329036", JSONObject.parseObject("{\"pwd\":\"1234567890\",\"mobile\":\"18444195885\",\"ip\":\"36.97.116.109\"}"));
		MOBILE_MAP.put("21342434", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"15240855569\" ,\"ip\":\"117.136.85.45\"}"));
		MOBILE_MAP.put("21294164", JSONObject.parseObject("{\"pwd\":\"qweasdzxc\",\"mobile\":\"13365157451\",\"ip\":\"49.70.63.51\"}"));
		MOBILE_MAP.put("21351499", JSONObject.parseObject("{\"pwd\":\"123456\",\"mobile\":\"18073598666\",\"ip\":\"113.218.21.19\"}"));
		MOBILE_MAP.put("21359560", JSONObject.parseObject("{\"pwd\":\"1234567890qwe\",\"mobile\":\"13438643569\" ,\"ip\":\"223.85.148.206\"}"));
		MOBILE_MAP.put("21355327", JSONObject.parseObject("{\"pwd\":\"2909213553\",\"mobile\":\"15869039302\",\"ip\":\"112.10.95.34\"}"));
		MOBILE_MAP.put("21322034", JSONObject.parseObject("{\"pwd\":\"drrczr\",\"mobile\":\"13764875060\" ,\"ip\":\"117.136.19.92\"}"));
		MOBILE_MAP.put("21358102", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"18547002210\",\"ip\":\"1.182.99.178\"}"));
		MOBILE_MAP.put("21295965", JSONObject.parseObject("{\"pwd\":\"w6789000\",\"mobile\":\"15217428458\",\"ip\":\"183.240.200.159\"}"));
		MOBILE_MAP.put("21347304", JSONObject.parseObject("{\"pwd\":\"123456789qwer\",\"mobile\":\"13202623935\",\"ip\":\"112.96.118.159\"}"));
		//180
		MOBILE_MAP.put("21276270", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"15578639236\",\"ip\":\"180.143.205.39\"}"));
		MOBILE_MAP.put("21307057", JSONObject.parseObject("{\"pwd\":\"hly123445\",\"mobile\":\"17732656237\" ,\"ip\":\"183.197.32.145\"}"));
		MOBILE_MAP.put("21316978", JSONObject.parseObject("{\"pwd\":\"lk152229\",\"mobile\":\"13647434972\" ,\"ip\":\"113.240.145.32\"}"));
		MOBILE_MAP.put("21325764", JSONObject.parseObject("{\"pwd\":\"1234567890\",\"mobile\":\"13117182693\" ,\"ip\":\"183.95.249.226\"}"));
		MOBILE_MAP.put("21328195", JSONObject.parseObject("{\"pwd\":\"zs234108\",\"mobile\":\"17855749172\",\"ip\":\"120.242.140.20\"}"));
		MOBILE_MAP.put("21350017", JSONObject.parseObject("{\"pwd\":\"2151515181813445\",\"mobile\":\"15851787284\",\"ip\":\"223.67.111.119\"}"));
		MOBILE_MAP.put("21359203", JSONObject.parseObject("{\"pwd\":\"1997425\",\"mobile\":\"13580725569\",\"ip\":\"157.122.114.166\"}"));
		MOBILE_MAP.put("21341935", JSONObject.parseObject("{\"pwd\":\"hnzt1100\",\"mobile\":\"17373976001\",\"ip\":\"222.240.67.246\"}"));
		MOBILE_MAP.put("21356593", JSONObject.parseObject("{\"pwd\":\"biao12933483\",\"mobile\":\"15016285518\",\"ip\":\"113.111.231.212\"}"));
		MOBILE_MAP.put("21348044", JSONObject.parseObject("{\"pwd\":\"1813988009\",\"mobile\":\"19970462101\",\"ip\":\"182.97.174.60\"}"));
		//190
		MOBILE_MAP.put("21348349", JSONObject.parseObject("{\"pwd\":\"890119\",\"mobile\":\"18312901052\",\"ip\":\"223.77.55.8\"}"));
		MOBILE_MAP.put("21309303", JSONObject.parseObject("{\"pwd\":\"13530723471\",\"mobile\":\"17379554797\",\"ip\":\"182.104.122.145\"}"));
		MOBILE_MAP.put("21373325", JSONObject.parseObject("{\"pwd\":\"123456\",\"mobile\":\"15001561290\",\"ip\":\"49.117.242.62\"}"));
		MOBILE_MAP.put("21363986", JSONObject.parseObject("{\"pwd\":\"guoqi1994\",\"mobile\":\"15699197850\",\"ip\":\"113.249.199.41\"}"));
		MOBILE_MAP.put("21372153", JSONObject.parseObject("{\"pwd\":\"w459981024\",\"mobile\":\"18655357620\" ,\"ip\":\"111.38.175.110\"}"));
		MOBILE_MAP.put("21376007", JSONObject.parseObject("{\"pwd\":\"905025\",\"mobile\":\"18523666383\",\"ip\":\"27.14.244.221\"}"));
		MOBILE_MAP.put("21347637", JSONObject.parseObject("{\"pwd\":\"930910\",\"mobile\":\"13280810309\",\"ip\":\"123.196.11.195\"}"));
		MOBILE_MAP.put("21369581", JSONObject.parseObject("{\"pwd\":\"hjh888hjh.\",\"mobile\":\"13534239496\",\"ip\":\"183.35.10.240\"}"));
		MOBILE_MAP.put("21352362", JSONObject.parseObject("{\"pwd\":\"13797042620a\",\"mobile\":\"18372122616\",\"ip\":\"117.154.47.165\"}"));
		MOBILE_MAP.put("21347674", JSONObject.parseObject("{\"pwd\":\"kuilog555\",\"mobile\":\"18739484843\",\"ip\":\"223.91.28.19\"}"));
		//200
		MOBILE_MAP.put("21318339", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"15627327190\",\"ip\":\"223.104.64.149\"}"));
		MOBILE_MAP.put("21327001", JSONObject.parseObject("{\"pwd\":\"y7588911\",\"mobile\":\"13866070490\",\"ip\":\"112.31.79.243\"}"));
		MOBILE_MAP.put("21373026", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"18224261873\",\"ip\":\"182.134.2.193\"}"));
		MOBILE_MAP.put("21334758", JSONObject.parseObject("{\"pwd\":\"aaa202092\",\"mobile\":\"13481445865\",\"ip\":\"117.140.44.79\"}"));
		MOBILE_MAP.put("21369080", JSONObject.parseObject("{\"pwd\":\"w84812269\",\"mobile\":\"13730696839\",\"ip\":\"118.114.112.11\"}"));
		MOBILE_MAP.put("21330465", JSONObject.parseObject("{\"pwd\":\"wodemima12345\",\"mobile\":\"15706705097\",\"ip\":\"122.238.113.54\"}"));
		MOBILE_MAP.put("21341300", JSONObject.parseObject("{\"pwd\":\"lirumin\",\"mobile\":\"15549389575\",\"ip\":\"218.200.129.33\"}"));
		MOBILE_MAP.put("21358756", JSONObject.parseObject("{\"pwd\":\"123456789\",\"mobile\":\"18385782198\",\"ip\":\"14.204.82.204\"}"));
		MOBILE_MAP.put("21359504", JSONObject.parseObject("{\"pwd\":\"1234567890\",\"mobile\":\"15162487913\",\"ip\":\"221.225.216.92\"}"));
		MOBILE_MAP.put("21320560", JSONObject.parseObject("{\"pwd\":\"1234567890l\",\"mobile\":\"18254123986\",\"ip\":\"223.104.189.152\"}"));
		//210
	}
	
	public static void main(String[] args) {
		init();
		for(String key : MOBILE_MAP.keySet()) {
			System.err.println("userId:" + key + "," + getIp(key)
					+ "," + getPwd(key) + "," + getMobile(key));
		}
	}
}
