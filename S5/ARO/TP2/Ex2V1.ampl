var minutes_TV >= 10;
var pages_magazines >= 0;
maximize personnes: 1000000 * pages_magazines + 1800000 * minutes_TV;
subject to budget : 10000 * pages_magazines + 20000 * minutes_TV <= 1000000;
