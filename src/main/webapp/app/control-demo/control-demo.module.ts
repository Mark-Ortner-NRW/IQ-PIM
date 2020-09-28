import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InnoqPimSharedModule } from '../shared/shared.module';
import { CONTROL_DEMO_ROUTE, ControlDemoComponent } from './';
import { PageOneComponent } from './page-one/page-one.component';
import { PageTwoComponent } from './page-two/page-two.component';
import { PageThreeComponent } from './page-three/page-three.component';
import { PageFourComponent } from './page-four/page-four.component';
import { ColorPickerModule } from 'primeng/colorpicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { MultiSelectModule } from 'primeng/multiselect';
import { ToolbarModule } from 'primeng/toolbar';
import { FileUploadModule } from 'primeng/fileupload';
import { DialogModule } from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@NgModule({
  imports: [
    InnoqPimSharedModule,
    BrowserAnimationsModule,
    TableModule,
    RouterModule.forRoot([CONTROL_DEMO_ROUTE], { useHash: true }),
    ColorPickerModule,
    ButtonModule,
    RippleModule,
    ToastModule,
    InputTextModule,
    MultiSelectModule,
    ToolbarModule,
    FileUploadModule,
    DialogModule,
    ConfirmDialogModule,
  ],
  declarations: [ControlDemoComponent, PageOneComponent, PageTwoComponent, PageThreeComponent, PageFourComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class InnoqPimAppControlDemoModule {}
