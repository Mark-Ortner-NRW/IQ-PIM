import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { InnoqPimSharedModule } from 'app/shared/shared.module';
import { InnoqPimCoreModule } from 'app/core/core.module';
import { InnoqPimAppRoutingModule } from './app-routing.module';
import { InnoqPimHomeModule } from './home/home.module';
import { InnoqPimEntityModule } from './entities/entity.module';
import { InnoqPimAppLandingPageModule } from './landing-page/landing-page.module';
import { InnoqPimAppControlDemoModule } from './control-demo/control-demo.module';

// PrinNG
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';
import { SliderModule } from 'primeng/slider';
import { MultiSelectModule } from 'primeng/multiselect';
import { ContextMenuModule } from 'primeng/contextmenu';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ProgressBarModule } from 'primeng/progressbar';
import { InputTextModule } from 'primeng/inputtext';

// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    InnoqPimSharedModule,
    InnoqPimCoreModule,
    InnoqPimHomeModule,
    InnoqPimAppLandingPageModule,
    InnoqPimAppControlDemoModule,
    // PrinNG
    BrowserModule,
    TableModule,
    CalendarModule,
    SliderModule,
    DialogModule,
    MultiSelectModule,
    ContextMenuModule,
    DropdownModule,
    ButtonModule,
    ToastModule,
    InputTextModule,
    ProgressBarModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    InnoqPimEntityModule,
    InnoqPimAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class InnoqPimAppModule {}
