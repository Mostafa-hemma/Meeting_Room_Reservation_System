import { Component, Input } from '@angular/core';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ChipsModule } from 'primeng/chips';
import { CommonModule } from '@angular/common';
import { EventModel } from '../../shared/eventModel';
import { DialogBasicDemo } from '../editpopup/editpopup.component';
import { DialogModule } from 'primeng/dialog';
import { EventImpl } from '@fullcalendar/core/internal';

@Component({
    selector: 'overlay-panel-basic-demo',
    templateUrl: './popup.component.html',
    standalone: true,
    imports: [OverlayPanelModule,DialogModule, ButtonModule, InputTextModule, ChipsModule, CommonModule]
})
export class OverlayPanelBasicDemo {
    @Input() event: any;

}

