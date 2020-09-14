import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InnoqPimSharedModule } from '../shared/shared.module';
import { CONTROL_DEMO_ROUTE, ControlDemoComponent } from './';
import { PageOneComponent } from './page-one/page-one.component';
import { PageTwoComponent } from './page-two/page-two.component';
import { ColorPickerModule } from 'primeng/colorpicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';

@NgModule({
  imports: [
    InnoqPimSharedModule,
    BrowserAnimationsModule,
    TableModule,
    RouterModule.forRoot([CONTROL_DEMO_ROUTE], { useHash: true }),
    ColorPickerModule,
  ],
  declarations: [ControlDemoComponent, PageOneComponent, PageTwoComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class InnoqPimAppControlDemoModule {}
