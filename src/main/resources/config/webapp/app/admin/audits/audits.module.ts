import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InnoqPimSharedModule } from '../../shared/shared.module';

import { AuditsComponent } from './audits.component';

import { auditsRoute } from './audits.route';

@NgModule({
  imports: [InnoqPimSharedModule, RouterModule.forChild([auditsRoute])],
  declarations: [AuditsComponent],
})
export class AuditsModule {}
