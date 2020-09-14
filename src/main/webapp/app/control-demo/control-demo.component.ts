import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-control-demo',
  templateUrl: './control-demo.component.html',
  styleUrls: ['control-demo.component.scss'],
})
export class ControlDemoComponent implements OnInit {
  message: string;

  constructor() {
    this.message = 'ControlDemoComponent message';
  }

  ngOnInit(): void {}
}
