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
    // jhipster-needle-angular-add-module JHipster will add new module here
    InnoqPimEntityModule,
    InnoqPimAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class InnoqPimAppModule {}
