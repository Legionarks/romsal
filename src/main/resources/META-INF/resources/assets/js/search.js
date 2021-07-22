$(function () {
  filter();
  fill();
});

function filter() {
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
}

function fill() {
  const params = (new URL(document.location)).searchParams;

  $("#address").val(params.get("address"));
  $("#type").val(params.get("type"));
  $("#room").val("")
  $("#category").val(params.get("category"));
  $("#room").val(params.get("room"));
  $("#bath").val(params.get("bath"));

  if (params.has("currency")) {
    $("input[name=currency][value=" + params.get("currency") + "]").click();

    if (params.has("price-min") && params.has("price-max")) {
      $("#price-min").val(params.get("price-min"));
      $("#price-max").val(params.get("price-max"));
      $("#slider-range").slider("values", 0, params.get("price-min"));
      $("#slider-range").slider("values", 1, params.get("price-max"));
      $("#amount").text($("input[name=currency]:checked").val() + "$" + params.get("price-min") + " - " + $("input[name=currency]:checked").val() + "$" + params.get("price-max"));
    }
  }
}

$("#clean").click(function () {
  $("#address").val("");
  $("#type").val($("#type option:first").val());
  $("#room").val("");
  $("#bath").val("");
  $("#category").val($("#category option:first").val());
  $("input[name=currency][value=USD]").click();
  $("#price-min").val($("#slider-range").slider("option", "min"));
  $("#price-max").val($("#slider-range").slider("option", "max"));
  $("#slider-range").slider("values", 0, $("#slider-range").slider("option", "min"));
  $("#slider-range").slider("values", 1, $("#slider-range").slider("option", "max"));
  $("#amount").text($("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 0) + " - " + $("input[name=currency]:checked").val() + "$" + $("#slider-range").slider("values", 1));
});