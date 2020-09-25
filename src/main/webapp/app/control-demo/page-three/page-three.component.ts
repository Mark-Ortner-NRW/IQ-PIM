import { Component, OnInit, ViewChild } from '@angular/core';
import { Brand, IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';
import { HttpResponse } from '@angular/common/http';

import { MessageService } from 'primeng/api';
// import { FilterUtils } from 'primeng/utils';
// import { LazyLoadEvent } from 'primeng/api';
// import { SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'jhi-page-one',
  templateUrl: './page-three.component.html',
  providers: [MessageService],
  styleUrls: ['page-three.component.scss'],
})
export class PageThreeComponent implements OnInit {
  color: any = '#24244c';
  brands?: IBrand[] | any;
  brands2?: IBrand[] | any;

  @ViewChild('dt') table: Table | undefined;

  loading: any = true;

  cols: any[] = [
    { field: 'id', header: 'ID' },
    { field: 'name', header: 'Name' },
    { field: 'description', header: 'Description' },
    { field: 'createdAt', header: 'Created At' },
  ];
  clonedBrands: { [n: number]: Brand } = {};

  constructor(protected brandService: BrandService, private messageService: MessageService) {}
  ngOnInit(): void {
    this.brandService.query({}).subscribe(
      (res: HttpResponse<IBrand[]>) => this.onSuccess(res.body),
      () => this.onError()
    );

    this.loading = false;
  }

  protected onSuccess(data: IBrand[] | null): void {
    this.brands = data || [];
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Got Data ... .' });
  }

  protected onError(): void {}

  public onRowEditSave(brand: Brand): void {
    delete this.brandService[brand.id || 0];
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Product is updated' });
  }

  onRowEditInit(brand: Brand): void {
    this.clonedBrands[brand.id || 0] = { ...brand };
  }

  onRowEditCancel(brand: Brand, index: number): void {
    this.brands2[index] = this.clonedBrands[brand.id || 0];
    delete this.brands2[brand.id || 0];
  }
}
