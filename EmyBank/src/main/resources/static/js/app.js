let slider1 = document.getElementById("loanRange");
let output1 = document.getElementById("loan");
let slider2 = document.getElementById("perRange");
let output2 = document.getElementById("per");
let slider3 = document.getElementById("durationRange");
let output3 = document.getElementById("duration");

output1.innerHTML = slider1.value;
output2.innerHTML = slider2.value;
output3.innerHTML = slider3.value;

slider1.oninput = function() {
	output1.innerHTML = this.value;
}

slider2.oninput = function() {
	output2.innerHTML = this.value;
}

slider3.oninput = function() {
	output3.innerHTML = this.value;
}

// EMI
document.querySelector('#find').addEventListener('click',function(e){
   
    e.preventDefault();
    // UI

  const UIamount = document.getElementById("loanRange").value;
  const UIinterest = document.getElementById("perRange").value;
  const UIyears = document.getElementById("durationRange").value;

  // Calculate

  const principal = parseFloat(UIamount);
  const CalculateInterest = parseFloat(UIinterest) / 100 / 12;
  const calculatedPayments = parseFloat(UIyears) * 12;

  //Compute monthly Payment

  const x = Math.pow(1 + CalculateInterest, calculatedPayments);
  const monthly = (principal * x * CalculateInterest) / (x - 1);
  const monthlyPayment = monthly.toFixed(2);

  //Compute Interest

  const totalInterest = (monthly * calculatedPayments - principal).toFixed(2);

  //Compute Total Payment

  const totalPayment = (monthly * calculatedPayments).toFixed(2);

  //Show results

  document.getElementById("monthlyPayment").innerHTML = "$" + monthlyPayment;

  document.getElementById("totalInterest").innerHTML = "%" + totalInterest;

  document.getElementById("totalPayment").innerHTML = "$" + totalPayment;    
})


//chart
var xValues = [50,60,70,80,90,100,110,120,130,140,150];
var yValues = [7,8,8,9,9,9,10,11,14,14,15];

new Chart("myChart", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      fill: false,
      lineTension: 0,
      backgroundColor: "rgba(0,0,255,1.0)",
      borderColor: "rgba(0,0,255,0.1)",
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    scales: {
      yAxes: [{ticks: {min: 6, max:16}}],
    }
  }
});