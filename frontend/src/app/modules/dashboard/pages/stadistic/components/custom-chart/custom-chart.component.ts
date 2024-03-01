import { Component } from '@angular/core';
import { ChartData, plugins } from 'chart.js';

@Component({
  selector: 'app-custom-chart',
  templateUrl: './custom-chart.component.html',
  styleUrls: ['./custom-chart.component.scss'],
})
export class CustomChartComponent {
  chartLabels = ['Sales'];
  chartOptions = {
    responsive: true,
  }
  datasets: ChartData <'line', {key: string, value: number} []> = {
    datasets: [{
      data: [
        {key: 'sem 1', value: 20},
        {key: 'sem 2 ', value: 10},
        {key: 'sem 3 ', value: 30},
        {key: 'sem 4 ', value: 40},
        {key: 'sem 5 ', value: 5},
      ],
      parsing: {
        xAxisKey: 'key',
        yAxisKey: 'value'
      },
      backgroundColor:"#FE6283",
      tension:0.5,
      label:"Greenpoints",
    }],
  };
}
