var emoji={
		emoji_001 : "[微笑]",
		emoji_002 : "[撇嘴]",
		emoji_003 : "[色]",
		emoji_004 : "[发呆]",
		emoji_005 : "[得意]",
		emoji_006 : "[流泪]",
		emoji_007 : "[害羞]",
		emoji_008 : "[闭嘴]",
		emoji_009 : "[睡]",
		emoji_010 : "[大哭]",
		emoji_011 : "[尴尬]",
		emoji_012 : "[发怒]",
		emoji_013 : "[调皮]",
		emoji_014 : "[呲牙]",
		emoji_015 : "[惊讶]",
		emoji_016 : "[难过]",
		emoji_017 : "[囧]",
		emoji_018 : "[抓狂]",
		emoji_019 : "[吐]",
		emoji_020 : "[偷笑]",
		emoji_021 : "[愉快]",
		emoji_022 : "[白眼]",
		emoji_023 : "[傲慢]",
		emoji_024 : "[困]",
		emoji_025 : "[惊恐]",
		emoji_026 : "[流汗]",
		emoji_027 : "[憨笑]",
		emoji_028 : "[悠闲]",
		emoji_029 : "[奋斗]",
		emoji_030 : "[咒骂]",
		emoji_031 : "[疑问]",
		emoji_032 : "[嘘]",
		emoji_033 : "[晕]",
		emoji_034 : "[衰]",
		emoji_035 : "[骷髅]",
		emoji_036 : "[敲打]",
		emoji_037 : "[再见]",
		emoji_038 : "[擦汗]",
		emoji_039 : "[抠鼻]",
		emoji_040 : "[鼓掌]",
		emoji_041 : "[坏笑]",
		emoji_042 : "[左哼哼]",
		emoji_043 : "[右哼哼]",
		emoji_044 : "[哈欠]",
		emoji_045 : "[鄙视]",
		emoji_046 : "[委屈]",
		emoji_047 : "[快哭了]",
		emoji_048 : "[阴险]",
		emoji_049 : "[亲亲]",
		emoji_050 : "[可怜]",
		emoji_051 : "[菜刀]",
		emoji_052 : "[西瓜]",
		emoji_053 : "[啤酒]",
		emoji_054 : "[咖啡]",
		emoji_055 : "[猪头]",
		emoji_056 : "[玫瑰]",
		emoji_057 : "[凋谢]",
		emoji_058 : "[嘴唇]",
		emoji_059 : "[爱心]",
		emoji_060 : "[心碎]",
		emoji_061 : "[蛋糕]",
		emoji_062 : "[炸弹]",
		emoji_063 : "[便便]",
		emoji_064 : "[月亮]",
		emoji_065 : "[太阳]",
		emoji_066 : "[拥抱]",
		emoji_067 : "[强]",
		emoji_068 : "[弱]",
		emoji_069 : "[握手]",
		emoji_070 : "[胜利]",
		emoji_071 : "[抱拳]",
		emoji_072 : "[勾引]",
		emoji_073 : "[拳头]",
		emoji_074 : "[OK]",
		emoji_075 : "[跳跳]",
		emoji_076 : "[发抖]",
		emoji_077 : "[怄火]",
		emoji_078 : "[转圈]",
		/*emoji_079 : "\\ue415",//中文无效[笑脸]
		emoji_080 : "\\ue40c",//中文无效[生病]
		emoji_081 : "\\ue412",//中文无效[破涕为笑]
		emoji_082 : "\\ue409",//中文无效[吐舌]
		emoji_083 : "\\ue40d",//中文无效[脸红]
		emoji_084 : "\\ue107",//中文无效[恐惧]
		emoji_085 : "\\ue403",//中文无效[失望]
		emoji_086 : "\\ue40e",//中文无效[无语]
*/		emoji_087 : "[嘿哈]",
		emoji_088 : "[捂脸]",
		emoji_089 : "[奸笑]",
		emoji_090 : "[机智]",
		emoji_091 : "[皱眉]",
		emoji_092 : "[耶]",
		/*emoji_093 : "\\ue11b",//中文无效[鬼魂]
		emoji_094 : "\\ue41d",//中文无效[合十]
		emoji_095 : "\\ue14c",//中文无效[强壮]
		emoji_096 : "\\ue312",//中文无效[庆祝]
		emoji_097 : "\\ue112",//中文无效[礼物]
*/		emoji_098 : "[红包]",
		emoji_099 : "[鸡]",
}

	String.prototype.expression = function(){
		var emoji = {
			"[微笑]":'emoji_001',
			"[撇嘴]":'emoji_002',
			"[色]":'emoji_003',
			"[发呆]":'emoji_004',
			"[得意]":'emoji_005',
			"[流泪]":'emoji_006',
			"[害羞]":'emoji_007',
			"[闭嘴]":'emoji_008',
			"[睡]":'emoji_009',
			"[大哭]":'emoji_010',
			"[尴尬]":"emoji_011",
			"[发怒]":'emoji_012',
			"[调皮]":'emoji_013',
			"[呲牙]":'emoji_014',
			"[龇牙]":'emoji_014',
			"[惊讶]":'emoji_015',
			"[难过]":'emoji_016',
			"[囧]":'emoji_017',
			"[抓狂]":'emoji_018',
			"[吐]":'emoji_019',
			"[偷笑]":'emoji_020',
			"[愉快]":'emoji_021',
			"[白眼]":'emoji_022',
			"[傲慢]":'emoji_023',
			"[困]":'emoji_024',
			"[惊恐]":'emoji_025',
			"[流汗]":'emoji_026',
			"[憨笑]":'emoji_027',
			"[悠闲]":'emoji_028',
			"[奋斗]":'emoji_029',
			"[咒骂]":'emoji_030',
			"[疑问]":'emoji_031',
			"[嘘]":'emoji_032',
			"[晕]":'emoji_033',
			"[衰]":'emoji_034',
			"[骷髅]":'emoji_035',
			"[敲打]":'emoji_036',
			"[再见]":'emoji_037',
			"[擦汗]":'emoji_038',
			"[抠鼻]":'emoji_039',
			"[鼓掌]":'emoji_040',
			"[坏笑]":'emoji_041',
			"[左哼哼]":'emoji_042',
			"[右哼哼]":'emoji_043',
			"[哈欠]":'emoji_044',
			"[鄙视]":'emoji_045',
			"[委屈]":'emoji_046',
			"[快哭了]":'emoji_047',
			"[阴险]":'emoji_048',
			"[亲亲]":'emoji_049',
			"[可怜]":'emoji_050',
			"[菜刀]":'emoji_051',
			"[西瓜]":'emoji_052',
			"[啤酒]":'emoji_053',
			"[咖啡]":'emoji_054',
			"[猪头]":'emoji_055',
			"[玫瑰]":'emoji_056',
			"[凋谢]":'emoji_057',
			"[嘴唇]":'emoji_058',
			"[爱心]":'emoji_059',
			"[心碎]":'emoji_060',
			"[蛋糕]":'emoji_061',
			"[炸弹]":'emoji_062',
			"[便便]":'emoji_063',
			"[月亮]":'emoji_064',
			"[太阳]":'emoji_065',
			"[拥抱]":'emoji_066',
			"[强]":'emoji_067',
			"[弱]":'emoji_068',
			"[握手]":'emoji_069',
			"[胜利]":'emoji_070',
			"[抱拳]":'emoji_071',
			"[勾引]":'emoji_072',
			"[拳头]":'emoji_073',
			"[OK]":'emoji_074',
			"[跳跳]":'emoji_075',
			"[发抖]":'emoji_076',
			"[怄火]":'emoji_077',
			"[转圈]":'emoji_078',
			"\ue415":'emoji_079',//中文无效[笑脸]
			"\ue40c":'emoji_080',//中文无效[生病]
			"\ue412":'emoji_081',//中文无效[破涕为笑]
			"\ue409":'emoji_082',//中文无效[吐舌]
			"\ue40d":'emoji_083',//中文无效[脸红]
			"\ue107":'emoji_084',//中文无效[恐惧]
			"\ue403":'emoji_085',//中文无效[失望]
			"\ue40e":'emoji_086',//中文无效[无语]
			
			"\\ue415":'emoji_079',//中文无效[笑脸]
			"\\ue40c":'emoji_080',//中文无效[生病]
			"\\ue412":'emoji_081',//中文无效[破涕为笑]
			"\\ue409":'emoji_082',//中文无效[吐舌]
			"\\ue40d":'emoji_083',//中文无效[脸红]
			"\\ue107":'emoji_084',//中文无效[恐惧]
			"\\ue403":'emoji_085',//中文无效[失望]
			"\\ue40e":'emoji_086',//中文无效[无语]
			"[嘿哈]":'emoji_087',
			"[捂脸]":'emoji_088',
			"[奸笑]":'emoji_089',
			"[机智]":'emoji_090',
			"[皱眉]":'emoji_091',
			"[耶]":'emoji_092',
			"\ue11b":'emoji_093',//中文无效[鬼魂]
			"\ue41d":'emoji_094',//中文无效[合十]
			"\ue14c":'emoji_095',//中文无效[强壮]
			"\ue312":'emoji_096',//中文无效[庆祝]
			"\ue112":'emoji_097',//中文无效[礼物]
			
			"\\ue11b":'emoji_093',//中文无效[鬼魂]
			"\\ue41d":'emoji_094',//中文无效[合十]
			"\\ue14c":'emoji_095',//中文无效[强壮]
			"\\ue312":'emoji_096',//中文无效[庆祝]
			"\\ue112":'emoji_097',//中文无效[礼物]
			"[红包]":'emoji_098',
			"[鸡]":'emoji_099',
		}
		var reg = /(\[[\w\-\u4e00-\u9fa5a]+\]|(\\ue415)|(\\ue40c)|(\\ue412)|(\\ue409)|(\\ue40d)|(\\ue107)|(\\ue403)|(\\ue40e)|(\\ue11b)|(\\ue41d)|(\\ue14c)|(\\ue312)|(\\ue112)|(\ue415)|(\ue40c)|(\ue412)|(\ue409)|(\ue40d)|(\ue107)|(\ue403)|(\ue40e)|(\ue11b)|(\ue41d)|(\ue14c)|(\ue312)|(\ue112))/g
		return this.replace(reg,function(val){
			if(!emoji[val]) return val;
			return '<img src="/oms-web/static/admin/images/emoji/'+ emoji[val] +'.png" width="32px">'
		});
	};
	
	
		
		
		




