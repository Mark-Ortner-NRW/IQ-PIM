import { Component, OnInit } from '@angular/core';
import { ColorPickerModule } from 'primeng/colorpicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'jhi-page-one',
  templateUrl: './page-one.component.html',
  styleUrls: ['page-one.component.scss'],
})
export class PageOneComponent implements OnInit {
  message: string;
  color: any = '#24244c';

  constructor() {
    this.message = 'PageOneComponent message';
  }
  ngOnInit(): void {}
}
