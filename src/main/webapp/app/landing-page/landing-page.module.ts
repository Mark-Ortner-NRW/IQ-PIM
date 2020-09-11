import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InnoqPimSharedModule } from '../shared/shared.module';

import { LANDING_PAGE_ROUTE, LandingPageComponent } from './';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forRoot([LANDING_PAGE_ROUTE], { useHash: true })],
  declarations: [LandingPageComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class InnoqPimAppLandingPageModule {}
