$(function () {
  const min = parseInt($("input[name=price-min]").val());
  const max = parseInt($("input[name=price-max]").val());
  const rates = parseFloat($("#slider-range").attr("rates"));

  $("#slider-range").slider({
    range: true,
    min: min,
    max: max,
    values: [min, max],
    slide: function (event, ui) {
      $("#price-min").val(ui.values[0]);
      $("#price-max").val(ui.values[1]);

      $("#amount").text($("input[name=currency]:checked").val() + "$" + ui.values[0] + " - " + $("input[name=currency]:checked").val() + "$" + ui.values[1]);
    }
  });

  $("input[name=currency]").change(function () {
    let low, high;

    if (this.value == 'USD') {
      low = parseInt($("#slider-range").slider("values", 0) / rates);
      high = parseInt($("#slider-range").slider("values", 1) / rates);

      $("#slider-range").slider("option", "min", min);
      $("#slider-range").slider("option", "max", max);
    } else if (this.value == 'DOP') {
      low = parseInt($("#slider-range").slider("values", 0) * rates);
      high = parseInt($("#slider-range").slider("values", 1) * rates);

      $("#slider-range").slider("option", "min", min * rates);
      $("#slider-range").slider("option", "max", max * rates);
    }

    $("input[name=price-min]").val(low);
    $("input[name=price-max]").val(high);
    $("#slider-range").slider("values", 0, low);
    $("#slider-range").slider("values", 1, high);
    $("#amount").text($("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 0) + " - " + $("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 1));
  });

  $("#amount").text($("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 0) + " - " + $("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 1));
});

$("#clean").click(function () {
  $("#address").val("");
  $("#type").val($("#type option:first").val());
  $("#bath").val("");
  $("#room").val("");
  $("#category").val($("#category option:first").val());
  $("#slider-range").slider("values", 0, $("#slider-range").slider("option", "min"));
  $("#slider-range").slider("values", 1, $("#slider-range").slider("option", "max"));
  $("#amount").text($("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 0) + " - " + $("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 1));
});