J_spiketTime = function(id, startTime, endTime) {
    if (endTime <= 0) return;
    var base = $("#" + id).find('span');
	var s0 = base.eq(0);
    var s1 = base.eq(1);
    var s2 = base.eq(2);
    var s3 = base.eq(3);
    var s4 = base.eq(4);
    var type = 1;
    var mm = 10;
    var interval;
    function formatTime(t) {
        if (t > 0) {
            var totalSecond = t / 1000;
            var minute = Math.floor(totalSecond / 60);
            var hour = Math.floor(minute / 60);
            minute = minute % 60;
            var second = Math.floor(totalSecond % 60);
            return [hour, minute, second];
        } else {
            clearInterval(interval);
            return [0, 0, 0, 0];			
        }
    };
    var timerLeft = function(t, e) {
        var start = (new Date()).getTime();
        return {
            getLeft: function() {
                var now = (new Date()).getTime();
                var s = t - (now - start);
                if (s <= 0 && type == 1) {
					s0.html("距离结束时间");					
                    this.getLeft = function() {
                        var now = (new Date()).getTime();
                        var s = e - (now - start);
                        return s;
                    };
                    return this.getLeft();
                }
                return s;
            },
            render: function() {
                var arr = formatTime(this.getLeft());
                s1.html(arr[0]);
                s2.html(arr[1]);
                s3.html(arr[2]);
            }
        };
    };
    var tl = timerLeft(startTime, endTime);
    tl.render();
    interval = setInterval(function() {
        mm--;
        s4.html(mm);
        if (mm == 0) {
            tl.render();
            mm = 10;
        }
    },
    100);
}

/* 
//结构如下
<div class="SpikeTime" id="Sn_spike_time">
	<span>距离开始时间：</span>
	<em>
		<span class="nan">56</span>
		<b>：</b>
		<span class="nan">56</span>
		<b>：</b>
		<span class="nan">56</span>
	</em>
</div>
*/